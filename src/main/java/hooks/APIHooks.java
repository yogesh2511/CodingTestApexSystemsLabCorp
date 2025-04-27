package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class APIHooks {

    @Before("@api")
    public void beforeAPIScenario(Scenario scenario) {
        System.out.println("Starting API Scenario: " + scenario.getName());
        
        // Enable detailed logging for API tests
        if (System.getProperty("debug", "false").equals("true")) {
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        }
    }

    @After("@api")
    public void afterAPIScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("API Scenario failed: " + scenario.getName());
            // You could log the last request/response here
        }
        
        // Reset REST Assured to default state
        RestAssured.reset();
        ScenarioContextManager.clear();
    }
   
}