package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
}
