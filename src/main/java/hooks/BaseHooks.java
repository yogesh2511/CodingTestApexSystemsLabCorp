package hooks;


import io.cucumber.java.*;
import pages.BasePage;

public class BaseHooks{

    @BeforeAll
    public static void beforeAll() {
        // Setup actions that run once before all scenarios
        System.out.println("=== Starting Test Run ===");
        
    }

    @AfterAll
    public static void afterAll() {
        // Teardown actions that run once after all scenarios
        System.out.println("=== Test Run Completed ===");
    }
}