package utilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementHelper {

	public static void cookiesHandler(WebDriver driver) {
		try {
			if (driver.findElement(By.xpath("//*[contains(text(),'Accept All Cookies')]")).isDisplayed()) {
				LoggerUtils.info("Cookies pop-up is displayed.");
				driver.findElement(By.xpath("//*[contains(text(),'Accept All Cookies')]")).click();
				LoggerUtils.info("Cookies pop-up accepted.");
			}
		} catch (Exception e) {
			LoggerUtils.info("Inside catch block" + e.getMessage());
			// Handle the case where the element is found or displayed
			if (driver.findElement(By.xpath("//*[contains(text(),'Accept All Cookies')]")).isDisplayed()) {
				JavaScriptUtils.clickElement(driver, By.xpath("//*[contains(text(),'Accept All Cookies')]"));
				LoggerUtils.info("Cookies pop-up accepted using JavaScript.");
			}
			LoggerUtils.info("Cookies pop-up not displayed or already accepted.");
		}
	}

	public static void switchToWindowWithRedirect(WebDriver driver, String expectedFinalTitle) {
		try {
			String parentWindow = driver.getWindowHandle();
			// Wait for new window to appear
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.numberOfWindowsToBe(2));

			// Switch to new window
			Set<String> windowHandles = driver.getWindowHandles();
			for (String winHandle : windowHandles) {
				if (!winHandle.equals(parentWindow)) {
					driver.switchTo().window(winHandle);

					// Wait for redirects to complete
					waitForRedirectToComplete(driver, expectedFinalTitle);
					return;
				}
			}

			throw new NoSuchWindowException("No new window found");
		} catch (Exception e) {
			LoggerUtils.error("Window switching failed: " + e.getMessage());
			throw e;
		}
	}

	private static void waitForRedirectToComplete(WebDriver driver, String expectedFinalTitle) {
		// Wait for URL to stabilize (stop changing)
		String currentUrl = "";
		int attempts = 0;
		while (attempts < 10) {
			String newUrl = driver.getCurrentUrl();
			if (newUrl.equals(currentUrl)) {
				break;
			}
			currentUrl = newUrl;
			attempts++;
			try {
				Thread.sleep(500); // Wait half second between checks
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		// Then wait for final title
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.titleIs(expectedFinalTitle));
	}

	public static void switchToParentWindow(WebDriver driver) {
		String parentWindow = AliasUtility.getValue("ParentWindow");
		driver.switchTo().window(parentWindow);
		LoggerUtils.info("Switched back to parent window: " + parentWindow);
	}

	public static void closeAllChildWindows(WebDriver driver) {
		String parentWindow = AliasUtility.getValue("ParentWindow");
		int closedWindows = 0;

		try {
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(parentWindow)) {
					driver.switchTo().window(handle);
					driver.close();
					closedWindows++;
				}
			}
			LoggerUtils.info("Closed " + closedWindows + " child windows");
			switchToParentWindow(driver);
		} catch (WebDriverException e) {
			LoggerUtils.error("Failed to close child windows: " + e.getMessage());
			throw e;
		}
	}
}
