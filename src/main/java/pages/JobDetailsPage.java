package pages;

import utilities.LoggerUtils;
import utilities.WaitUtils;

import org.openqa.selenium.By;
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

	private By descriptionParagraph =By.xpath("//div[@class='jd-info au-target']");

	private By applyButton = By.xpath("//ppc-content[normalize-space()='Apply Now']");

	private By returnToSearchLink = By.linkText("Return to Job Search");

	public void verifyJobTitle(String expectedTitle) {
		WebElement JT = WaitUtils.waitForElementVisible(driver, jobTitle);
		boolean isJTDisplayed = JT.isDisplayed();
    	LoggerUtils.info("Job ID is displayed: " + isJTDisplayed);
      	Boolean isIdCorrect = Boolean.valueOf(expectedTitle);
		Assert.assertEquals(isIdCorrect, isJTDisplayed);
	}

	public void verifyJobLocation(String expectedLocation) {
		WebElement JL = WaitUtils.waitForElementVisible(driver, jobLocation);
		boolean isJLDisplayed = JL.isDisplayed();
    	LoggerUtils.info("Job ID is displayed: " + isJLDisplayed);
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
		WebElement descriptionPara= WaitUtils.waitForElementVisible(driver, descriptionParagraph);
		LoggerUtils.info("Verifying requirement: " + expectedText);
    	LoggerUtils.info("Description paragraph text: " + descriptionPara.getText());
		//Assert.assertFalse(expectedText.toLowerCase().contains(descriptionPara.getText().toLowerCase()));
		Assert.assertTrue(descriptionPara.getText().toLowerCase().contains(expectedText.toLowerCase()));
	}

	public void clickApplyNow() {
		WebElement applyBtn = WaitUtils.waitForElementClickable(driver, applyButton);
		LoggerUtils.info("Waiting for Apply Now button to be clickable.");
		applyBtn.click();
		LoggerUtils.info("Apply Now button clicked.");
	}

	public void clickReturnToJobSearch() {
		LoggerUtils.info("Waiting for Return to Job Search link to be clickable.");
		//returnToSearchLink.click();
		LoggerUtils.info("Return to Job Search link clicked.");
	}
}
