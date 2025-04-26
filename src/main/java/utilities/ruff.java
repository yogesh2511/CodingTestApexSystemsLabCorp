package utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ruff {

	@Test
	public static void test1() {
		System.out.println("Hello, World!");
		
		RestAssured.baseURI = Config.getApiBaseUrl();
		
		 Response response = given().header("Content-Type", "application/json").when()
				.get("https://echo.free.beeceptor.com/sample-request?author=beeceptor");
	
			
		        // Validate HTTP Status
		        response.then().statusCode(200);

		        
		        // Validate Path
		        response.then().body("path", equalTo("/sample-request?author=beeceptor"));

		        System.out.println("response"+response.asPrettyString());
		        Assert.assertEquals(response.getStatusCode(), 200);
		        Assert.assertEquals(response.body().jsonPath().get("ip"), "103.241.226.3:32494");
		        Assert.assertEquals(response.body().jsonPath().get("headers.Host"), "echo.free.beeceptor.com");
		        
		        // response.then().body("ip", em);
		        // Validate IP Address
		       // response.then().body("ip",assigned("ip"));
                            
		        // Validate Headers
//		        response.then().body("headers.Host", equalTo("echo.free.beeceptor.com"))
//		                     .body("headers.User-Agent", containsString("Apache-HttpClient"))
//		                     .body("headers.Accept", equals("*/*"))
//		                     .body("headers.Accept-Encoding", equals("gzip,deflate"))
//		                     .body("headers.Content-Type", equals("application/json"));
//
//		        // Validate Parsed Query Params
//		        response.then().body("parsedQueryParams.author", equals("beeceptor"));
//
//		        // Validate Raw Body Error
//		        response.then().body("warnings", hasItem(containsString("Error in parsing JSON body")));
//		    
}
}
