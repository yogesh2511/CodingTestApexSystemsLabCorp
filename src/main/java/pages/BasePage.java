package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.DriverManager;
import utilities.WaitUtils;

public class BasePage {
	protected WebDriver driver;

	public BasePage() {
		this.driver = DriverManager.getDriver();
	}

	public void navigateTo(String url) {
		driver.get(url);
	}

	public void clickElement(String locator) {
		driver.findElement(By.xpath(locator)).click();
	}

	public void clickElement(WebElement locator) {
		WaitUtils.waitForElementClickable(driver, locator);
	}

	public void enterText(String locator, String text) {
		driver.findElement(By.xpath(locator)).sendKeys(text);
	}

	public String getElementText(String locator) {
		return driver.findElement(By.xpath(locator)).getText();
	}
}