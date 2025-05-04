package pages;

import utilities.AliasUtility;
import utilities.LoggerUtils;
import utilities.WaitUtils;
import utilities.WebElementHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class JobDetailsPage extends BasePage {

	public JobDetailsPage() {
		super();
	}

	private By jobTitle = By.cssSelector("h1.job-title");

	private By jobLocation = By.cssSelector("span.au-target.job-location");

	private By jobId = By.cssSelector("span.au-target.jobId");

	private By descriptionParagraph = By.xpath("//div[@class='jd-info au-target']");

	private By applyButton = By.xpath("//ppc-content[normalize-space()='Apply Now']");

	private By returnToSearchLink = By.linkText("Return to Job Search");

	public void verifyJobTitle(String expectedTitle) {
		WebElement JT = WaitUtils.waitForElementVisible(driver, jobTitle);
		boolean isJTDisplayed = JT.isDisplayed();
		LoggerUtils.info("Job Title: " + JT.getText());
		AliasUtility.storeAlias("JobTitle", JT.getText());
		LoggerUtils.info("Job Title is displayed: " + isJTDisplayed);
		Boolean isIdCorrect = Boolean.valueOf(expectedTitle);
		Assert.assertEquals(isIdCorrect, isJTDisplayed);
	}

	public void verifyJobLocation(String expectedLocation) {
		WebElement JL = WaitUtils.waitForElementVisible(driver, jobLocation);
		boolean isJLDisplayed = JL.isDisplayed();
		LoggerUtils.info("Job Location is displayed: " + isJLDisplayed);
		Boolean isIdCorrect = Boolean.valueOf(expectedLocation);
		Assert.assertEquals(isIdCorrect, isJLDisplayed);
	}

	public void verifyJobId(String expectedId) {
		WebElement JId = WaitUtils.waitForElementVisible(driver, jobId);
		boolean isIdDisplayed = JId.isDisplayed();
		LoggerUtils.info("Job ID is displayed: " + isIdDisplayed);
		Boolean isIdCorrect = Boolean.valueOf(expectedId);
		Assert.assertEquals(isIdCorrect, isIdDisplayed);
	}

	public void verifyDescriptionText(String expectedText) {
		WebElement descriptionPara = WaitUtils.waitForElementVisible(driver, descriptionParagraph);
		String actualText = descriptionPara.getText();
		LoggerUtils.info("Description: "+actualText);
		LoggerUtils.info("Verifying requirement: " + expectedText);
		//Assert.assertEquals(actualText, expectedText, "Texts do not match");
		//Assert.assertTrue(actualText.contains(expectedText), "The actual text should contain: " + expectedText);
		Assert.assertTrue(descriptionPara.getText().toLowerCase().contains(expectedText.toLowerCase()));
	}

	public void clickApplyNow() {
		String parentWindow = driver.getWindowHandle();
		LoggerUtils.info("Parent window handle: " + parentWindow);
		AliasUtility.storeAlias("ParentWindow", parentWindow);
        LoggerUtils.info("Parent window stored in alias: " + parentWindow);
		WebElement applyBtn = WaitUtils.waitForElementClickable(driver, applyButton);
		LoggerUtils.info("Waiting for Apply Now button to be clickable.");
		applyBtn.click();
		LoggerUtils.info("Apply Now button clicked.");
	}

	public void clickReturnToJobSearch() {
		LoggerUtils.info("Waiting for Return to Job Search link to be clickable.");
		WebElementHelper.closeAllChildWindows(driver);
		LoggerUtils.info("Return to Job Search link clicked.");
	}
}
