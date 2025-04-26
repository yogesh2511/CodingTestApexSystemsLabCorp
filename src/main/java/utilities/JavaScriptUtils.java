package utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {
    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public static void scrollToElement(WebDriver driver, By element) {
    	scrollToElement(driver,driver.findElement(element));		
    }

	public static void scrollToTop(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
	}
	public static void scrollToBottom(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public static void clickElement(WebDriver driver, WebElement element) {
		scrollToElement(driver, element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public static void clickElement(WebDriver driver, By element) {
		scrollToElement(driver, element);
		clickElement(driver, driver.findElement(element));
	}
	public static void enterText(WebDriver driver, WebElement element, String text) {
		scrollToElement(driver, element);
		((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + text + "');", element);
		//((JavascriptExecutor) driver).executeScript("arguments[0].value='" + text + "';", element);
	}
	public static void enterText(WebDriver driver, By element, String text) {
		scrollToElement(driver, element);
		enterText(driver, driver.findElement(element), text);
	}
    public static void waitForPageLoad(WebDriver driver) {
       ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }
}