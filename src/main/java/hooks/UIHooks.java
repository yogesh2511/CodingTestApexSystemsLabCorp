package hooks;

import utilities.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class UIHooks {

    @Before("@ui")
    public void beforeUIScenario(Scenario scenario) {
        System.out.println("Starting UI Scenario: " + scenario.getName());
        DriverManager.initDriver(); // <-- Initialize driver for this thread
    }

    @After("@ui")
    public void afterUIScenario(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver(); // <-- Always fetch the driver freshly
        if (scenario.isFailed() && driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        DriverManager.quitDriver(); // <-- Quit driver after scenario
    }
}
