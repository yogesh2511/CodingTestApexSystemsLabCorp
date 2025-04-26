package pages;


import utilities.WaitUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class JobDetailsPage extends BasePage {
     
    public JobDetailsPage() {
		super();
	  }

    private WebElement jobTitle = WaitUtils.waitForElementVisible(driver, By.cssSelector(".job-title"));

    private WebElement jobLocation = WaitUtils.waitForElementVisible(driver, By.cssSelector(".job-location"));

    private WebElement jobId = WaitUtils.waitForElementVisible(driver, By.cssSelector(".job-id"));

    private WebElement descriptionParagraph = WaitUtils.waitForElementVisible(driver, By.cssSelector(".job-description p:nth-child(3)"));

    private WebElement thirdRequirement = WaitUtils.waitForElementVisible(driver, By.cssSelector(".job-requirements li:nth-child(3)"));

    private WebElement applyButton = WaitUtils.waitForElementVisible(driver, By.id("apply-button"));

    @FindBy(linkText = "Return to Job Search")
    private WebElement returnToSearchLink;
    public void verifyJobTitle(String expectedTitle) {
        WaitUtils.waitForElementVisible(driver, jobTitle);
        Assert.assertEquals(jobTitle.getText(), expectedTitle);
    }

    public void verifyJobLocation(String expectedLocation) {
        Assert.assertEquals(jobLocation.getText(), expectedLocation);
    }

    public void verifyJobId(String expectedId) {
        Assert.assertEquals(jobId.getText(), expectedId);
    }

    public void verifyDescriptionText(String expectedText) {
        Assert.assertTrue(descriptionParagraph.getText().contains(expectedText));
    }

    public void verifyRequirement(String expectedRequirement) {
        Assert.assertTrue(thirdRequirement.getText().contains(expectedRequirement));
    }

    public void clickApplyNow() {
        WaitUtils.waitForElementClickable(driver, applyButton);
        applyButton.click();
    }

    public void clickReturnToJobSearch() {
        returnToSearchLink.click();
    }
}
