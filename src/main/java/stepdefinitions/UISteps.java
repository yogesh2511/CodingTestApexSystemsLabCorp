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
      
    }

    @When("I click on Careers link")
    public void clickCareersLink() {
        homePage.clickCareersLink();
      
    }

    @When("I search for {string} position")
    public void searchForPosition(String position) {
        careersPage = new CareersPage();
        careersPage.searchForPosition(position);
       
    }

    @When("I select the first matching position")
    public void selectFirstPosition() {
        careersPage.selectFirstPosition();
      
    }

    @Then("I should see the correct job details:")
    public void verifyJobDetails(Map<String, String> expectedDetails) {
        jobDetailsPage = new JobDetailsPage();
        jobDetailsPage.verifyJobTitle(expectedDetails.get("Job Title"));
    
        LoggerUtils.info("Verified job title: " + expectedDetails.get("Job Title"));
        jobDetailsPage.verifyJobLocation(expectedDetails.get("Job Location"));
        
        LoggerUtils.info("Verified job location: " + expectedDetails.get("Job Location"));
       
        LoggerUtils.info("Verified job ID: " + expectedDetails.get("Job ID"));
        jobDetailsPage.verifyJobId(expectedDetails.get("Job ID"));
       
        LoggerUtils.info("Verified job description: " + expectedDetails.get("Description Text"));
        jobDetailsPage.verifyDescriptionText(expectedDetails.get("Description Text"));
       
        LoggerUtils.info("Verified job qualifications: " + expectedDetails.get("Qualifications"));
        jobDetailsPage.verifyDescriptionText(expectedDetails.get("Qualifications"));
       
        LoggerUtils.info("Verified job responsibilities: " + expectedDetails.get("Responsibilities"));
        jobDetailsPage.verifyDescriptionText(expectedDetails.get("Responsibilities"));
       
        jobDetailsPage.verifyDescriptionText(expectedDetails.get("Requirement1"));
        
        LoggerUtils.info("Verified job requirements: " + expectedDetails.get("Requirement2"));
        jobDetailsPage.verifyDescriptionText(expectedDetails.get("Requirement2"));
        
        LoggerUtils.info("Verified job requirements: " + expectedDetails.get("Requirement3"));
        jobDetailsPage.verifyDescriptionText(expectedDetails.get("Requirement3"));
    }

    @When("I click on Apply Now button")
    public void clickApplyNow() {
        jobDetailsPage.clickApplyNow();
       
        LoggerUtils.info("Clicked on Apply Now button");
    }

  
    @When("I click to return to job search")
    public void clickReturnToJobSearch() {
        jobDetailsPage.clickReturnToJobSearch();
       
        LoggerUtils.info("Clicked to return to job search");
    }
    
	@Then("^I should see the same \"([^\"]*)\" on the application page$")
	public void iShouldSeeTheSameOnTheApplicationPage(String title) throws Throwable {
		applicationPage = new ApplicationPage();
		applicationPage.pageTitleVerification(title);
		
		LoggerUtils.info("Verified page title: " + title);
	}
	
	@Then("I should be back to the search results page")
    public void verifySearchResultsPage() {
		applicationPage.backToJobDetails();
		
		LoggerUtils.info("Verified back to search results page");
    }
	
	
	

}