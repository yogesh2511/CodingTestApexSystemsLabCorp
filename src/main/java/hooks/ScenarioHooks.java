package hooks;

import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import utilities.Config;

public class ScenarioHooks {

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        System.out.println("Before step: " + scenario.getLine());
        RestAssured.baseURI = Config.getApiBaseUrl();
       }

    @AfterStep
    public void afterStep(Scenario scenario) {
        System.out.println("After step: " + scenario.getLine());
    }
}