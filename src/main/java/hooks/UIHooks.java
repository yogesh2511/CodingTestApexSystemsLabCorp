package hooks;


import utilities.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class UIHooks {

	 private WebDriver driver;

    @Before("@ui")
    public void beforeUIScenario(Scenario scenario) {
        System.out.println("Starting UI Scenario: " + scenario.getName());
        driver = DriverManager.getInstance().getDriver(); 
    }

    @After("@ui")
    public void afterUIScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        DriverManager.getInstance().quitDriver();
		System.out.println("Finished UI Scenario: " + scenario.getName());
    }
}