package pages;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.DriverManager;
import utilities.ExtentManager;
import utilities.WaitUtils;

public class BasePage {
	protected WebDriver driver;
	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	protected static ExtentReports extent = ExtentManager
			.createInstance(System.getProperty("user.dir") + "\\report\\" + fileName);
	protected static ExtentTest test;

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