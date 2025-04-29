package stepdefinitions;


import pages.ApplicationPage;
import pages.BasePage;
import pages.CareersPage;
import pages.HomePage;
import pages.JobDetailsPage;
import utilities.LoggerUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;

public class UISteps extends BasePage {
    private HomePage homePage;
    private CareersPage careersPage;
    private JobDetailsPage jobDetailsPage;
    private ApplicationPage applicationPage;
    
    @Given("I navigate to LabCorp homepage")
    public void navigateToHomepage() {
        homePage = new HomePage();
        homePage.navigateTo();
        test.info("Navigated to LabCorp homepage");
    }

    @When("I click on Careers link")
    public void clickCareersLink() {
        homePage.clickCareersLink();
        test.info("Clicked on Careers link");
    }

    @When("I search for {string} position")
    public void searchForPosition(String position) {
        careersPage = new CareersPage();
        careersPage.searchForPosition(position);
        test.info("Searched for position: " + position);
    }

    @When("I select the first matching position")
    public void selectFirstPosition() {
        careersPage.selectFirstPosition();
        test.info("Selected the first matching position");
    }

    @Then("I should see the correct job details:")
    public void verifyJobDetails(Map<String, String> expectedDetails) {
        jobDetailsPage = new JobDetailsPage();
        jobDetailsPage.verifyJobTitle(expectedDetails.get("Job Title"));
        test.info("Verified job title: " + expectedDetails.get("Job Title"));
        LoggerUtils.info("Verified job title: " + expectedDetails.get("Job Title"));
        jobDetailsPage.verifyJobLocation(expectedDetails.get("Job Location"));
        test.info("Verified job location: " + expectedDetails.get("Job Location"));
        LoggerUtils.info("Verified job location: " + expectedDetails.get("Job Location"));
        test.info("Verified job ID: " + expectedDetails.get("Job ID"));
        LoggerUtils.info("Verified job ID: " + expectedDetails.get("Job ID"));
        jobDetailsPage.verifyJobId(expectedDetails.get("Job ID"));
        test.info("Verified job description: " + expectedDetails.get("Description Text"));
        LoggerUtils.info("Verified job description: " + expectedDetails.get("Description Text"));
        jobDetailsPage.verifyDescriptionText(expectedDetails.get("Description Text"));
        test.info("Verified job qualifications: " + expectedDetails.get("Qualifications"));
        LoggerUtils.info("Verified job qualifications: " + expectedDetails.get("Qualifications"));
        jobDetailsPage.verifyDescriptionText(expectedDetails.get("Qualifications"));
        test.info("Verified job responsibilities: " + expectedDetails.get("Responsibilities"));
        LoggerUtils.info("Verified job responsibilities: " + expectedDetails.get("Responsibilities"));
        jobDetailsPage.verifyDescriptionText(expectedDetails.get("Responsibilities"));
        test.info("Verified job requirements: " + expectedDetails.get("Requirement1"));
        jobDetailsPage.verifyDescriptionText(expectedDetails.get("Requirement1"));
        test.info("Verified job requirements: " + expectedDetails.get("Requirement2"));
        LoggerUtils.info("Verified job requirements: " + expectedDetails.get("Requirement2"));
        jobDetailsPage.verifyDescriptionText(expectedDetails.get("Requirement2"));
        test.info("Verified job requirements: " + expectedDetails.get("Requirement3"));
        LoggerUtils.info("Verified job requirements: " + expectedDetails.get("Requirement3"));
        jobDetailsPage.verifyDescriptionText(expectedDetails.get("Requirement3"));
    }

    @When("I click on Apply Now button")
    public void clickApplyNow() {
        jobDetailsPage.clickApplyNow();
        test.info("Clicked on Apply Now button");
        LoggerUtils.info("Clicked on Apply Now button");
    }

  
    @When("I click to return to job search")
    public void clickReturnToJobSearch() {
        jobDetailsPage.clickReturnToJobSearch();
        test.info("Clicked to return to job search");
        LoggerUtils.info("Clicked to return to job search");
    }
    
	@Then("^I should see the same \"([^\"]*)\" on the application page$")
	public void iShouldSeeTheSameOnTheApplicationPage(String title) throws Throwable {
		applicationPage = new ApplicationPage();
		applicationPage.pageTitleVerification(title);
		test.info("Verified page title: " + title);
		LoggerUtils.info("Verified page title: " + title);
	}
	
	@Then("I should be back to the search results page")
    public void verifySearchResultsPage() {
		applicationPage.backToJobDetails();
		test.info("Verified back to search results page");
		LoggerUtils.info("Verified back to search results page");
    }
	
	
	

}