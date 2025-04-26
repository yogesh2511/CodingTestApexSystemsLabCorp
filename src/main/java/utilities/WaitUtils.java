package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
    private static final int DEFAULT_TIMEOUT = 10;

    public static WebElement waitForElementVisible(WebDriver driver,WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }
    
    public static WebElement waitForElementVisible(WebDriver driver,By element) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static WebElement waitForElementClickable(WebDriver driver, By element) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public static WebElement waitForElementClickable(WebDriver driver,WebElement applyButton) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(applyButton));
    }

    public static void waitForElementToDisappear(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
	public static void waitForPageTitle(WebDriver driver, String title) {
		new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT)).until(ExpectedConditions.titleIs(title));
	}

	public static WebElement isElementPresent(WebDriver driver, By careersLinkLocator) {
		return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
				.until(ExpectedConditions.presenceOfElementLocated(careersLinkLocator));
	}
}
