package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/main/resources/features", 
		glue = {"stepdefinitions", "hooks"},
		plugin = { "pretty",		
		"html:target/cucumber-reports.html",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)
public class TestNgRunner extends AbstractTestNGCucumberTests {
	// This class will be empty. It is used only as a holder for the above
	// annotations
	// and to run the Cucumber tests with TestNG.

}
