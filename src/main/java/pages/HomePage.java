package pages;

import utilities.JavaScriptUtils;
import utilities.LoggerUtils;
import utilities.Config;
import utilities.WaitUtils;
import utilities.WebElementHelper;

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
        LoggerUtils.info("Navigated to LabCorp homepage: " + Config.getBaseUrl());
        WebElementHelper.cookiesHandler(driver);
        LoggerUtils.info("Cookies handled.");
     //   WaitUtils.waitForElementVisible(driver, driver.findElement(careersLinkLocator));
    }

    public void clickCareersLink() {
		try {
			WebElementHelper.cookiesHandler(driver);
	         LoggerUtils.info("Cookies handled.");
    	if (WaitUtils.isElementPresent(driver, careersLinkLocator).isDisplayed()) {
			LoggerUtils.info("Careers link is displayed, clicking on it.");
    		clickElement(WaitUtils.isElementPresent(driver, careersLinkLocator));
		} else {
			LoggerUtils.info("Careers link is not displayed, attempting to open the menu.");
		 WaitUtils.waitForElementVisible(driver, menu).click();
		 LoggerUtils.info("Menu is visible, clicking on it.");
		 //JavaScriptUtils.clickElement(driver, menu);
		 LoggerUtils.info("Menu is opened, clicking on Careers link.");
		 JavaScriptUtils.clickElement(driver, careersLinkLocator);
		 LoggerUtils.info("Careers link is clicked.");
		}
        WaitUtils.waitForPageTitle(driver, careerPageTitle);
        LoggerUtils.info("Waiting for page title to be: " + careerPageTitle);
        Assert.assertEquals(driver.getTitle(), careerPageTitle, "Page title does not match expected title.");
        LoggerUtils.info("Page title verified: " + careerPageTitle);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to click on Careers link: " + e.getMessage());
		}
	}

	public void verifyPageTitle() {
		String currentTitle = driver.getTitle();
		if (currentTitle.equals(careerPageTitle)) {
			LoggerUtils.info("Page title is correct: " + currentTitle);
			System.out.println("Page title is correct: " + currentTitle);
		} else {
			Assert.fail("Expected page title: " + careerPageTitle + ", but got: " + currentTitle);
		}
    }
}