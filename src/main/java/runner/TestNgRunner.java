package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/main/resources/features", 
		glue = {"stepdefinitions", "hooks"},
		plugin = { "pretty",		
		"html:test-output/target/cucumber-reports.html",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)
public class TestNgRunner extends AbstractTestNGCucumberTests {
	// This class is intentionally left empty. It serves as a TestNG runner for
	// Cucumber tests.
	// The CucumberOptions annotation specifies the location of feature files and
	// step definitions.
	// The plugin option configures the reporting format for the test results.
}
