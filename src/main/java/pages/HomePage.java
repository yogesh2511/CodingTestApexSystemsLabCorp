package pages;

import utilities.JavaScriptUtils;
import utilities.Config;
import utilities.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomePage extends BasePage {

    public HomePage() {
        super();  
    }

    private By careersLinkLocator = By.xpath("//a[normalize-space()='Careers']");
    private String careerPageTitle = "Careers at Labcorp | Embrace Possibilities, Change Lives";
    private By menu = By.xpath("//button[@aria-label='Open global Navigation']");

    public void navigateTo() {
        driver.get(Config.getBaseUrl());
        WaitUtils.waitForElementVisible(driver, driver.findElement(careersLinkLocator));
    }

    public void clickCareersLink() {
		if (WaitUtils.isElementPresent(driver, careersLinkLocator).isDisplayed()) {
			clickElement(WaitUtils.isElementPresent(driver, careersLinkLocator));
		} else {
		 WaitUtils.waitForElementVisible(driver, menu).click();
		 JavaScriptUtils.clickElement(driver, menu);
		 WaitUtils.waitForElementVisible(driver, careersLinkLocator).click();
		}
        WebElement careersLink = WaitUtils.waitForElementClickable(driver,careersLinkLocator);  // Fetching WebElement inside method
        WaitUtils.waitForElementClickable(driver, careersLink);
        careersLink.click();
        WaitUtils.waitForPageTitle(driver, careerPageTitle);
        Assert.assertEquals(driver.getTitle(), careerPageTitle, "Page title does not match expected title.");
    }
}