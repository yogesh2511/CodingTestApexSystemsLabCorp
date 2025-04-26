package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import utilities.FileUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.clients.Order;
import api.models.RestClient;
import hooks.ScenarioContextManager;

public class ApiSteps {
    private Response response;
    private Object requestBody;
    private RestClient client = new RestClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    // -------- Request Sending --------

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = RestClient.get(endpoint);
        ScenarioContextManager.setResponse(response);
        ScenarioContextManager.setResponseBodyAsMap(response.jsonPath().getMap(""));
    }

    @When("I send a PUT request to {string}")
    public void sendPutRequest(String endpoint) {
        response = RestClient.put(endpoint, requestBody);
        ScenarioContextManager.setResponse(response);
        ScenarioContextManager.setResponseBodyAsMap(response.jsonPath().getMap(""));
    }

    @When("I send a PATCH request to {string}")
    public void sendPatchRequest(String endpoint) {
        response = RestClient.patch(endpoint, requestBody);
        ScenarioContextManager.setResponse(response);
        ScenarioContextManager.setResponseBodyAsMap(response.jsonPath().getMap(""));
    }

    @When("I send a DELETE request to {string}")
    public void sendDeleteRequest(String endpoint) {
        response = RestClient.delete(endpoint);
        ScenarioContextManager.setResponse(response);
        ScenarioContextManager.setResponseBodyAsMap(response.jsonPath().getMap(""));
    }

    @When("I send a POST request to {string}")
    public void sendPostRequest(String endpoint) {
        RestClient.reset();
        response = (requestBody == null || requestBody.toString().isEmpty()) 
            ? RestClient.post(endpoint) 
            : RestClient.post(endpoint, requestBody);

        ScenarioContextManager.setResponse(response);
        ScenarioContextManager.setResponseBodyAsMap(response.jsonPath().getMap(""));
    }

    @When("I send a {string} request to {string} with:")
    public void sendRequestWithParams(String method, String endpoint, Map<String, String> params) {
        client = RestClient.reset().withHeaders(params).withQueryParams(params);

        switch (method.toUpperCase()) {
            case "GET" -> response = client.get(endpoint);
            case "POST" -> response = client.post(endpoint, RestClient.getRequestBody());
            case "PUT" -> response = client.put(endpoint, RestClient.getRequestBody());
            case "PATCH" -> response = client.patch(endpoint, RestClient.getRequestBody());
            case "DELETE" -> response = client.delete(endpoint);
            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

        ScenarioContextManager.setResponse(response);
        ScenarioContextManager.setResponseBodyAsMap(response.jsonPath().getMap(""));
    }

    @Given("I have the order payload from file {string}")
    public void loadOrderPayload(String fileName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("testdata/" + fileName);
            if (resource == null) {
                throw new IllegalArgumentException("File not found! " + fileName);
            }
            Path path = Paths.get(resource.toURI());
            String payload = new String(Files.readAllBytes(path));
            RestClient.setRequestBody(payload);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load payload file: " + fileName, e);
        }
    }

    // -------- Response Validations --------

    @Then("the response status code should be {int}")
    public void validateStatusCode(int expectedStatusCode) {
        assertEquals(RestClient.getStatusCode(), expectedStatusCode, "Unexpected status code");
    }

    @Then("the response should contain header {string}")
    public void validateHeader(String headerName) {
        assertTrue(RestClient.hasHeader(headerName), "Header not found: " + headerName);
    }

    @Then("the response body should contain {string}")
    public void validateResponseBody(String expectedContent) {
        String body = RestClient.getResponseBodyAsString();
        assertTrue(body.contains(expectedContent), "Response body does not contain: " + expectedContent);
    }

    @Then("the response should have valid path")
    public void validatePathInResponse() {
        String path = ScenarioContextManager.getResponse().jsonPath().getString("path");
        assertNotNull(path, "Path should not be null in response");
        assertFalse(path.isEmpty(), "Path should not be empty in response");
    }

    @Then("the response should have valid ip")
    public void validateIpInResponse() {
        String ip = ScenarioContextManager.getResponse().jsonPath().getString("ip");
        assertNotNull(ip, "IP should not be null in response");
        assertFalse(ip.isEmpty(), "IP should not be empty in response");
    }

    @Then("the response should contain headers:")
    public void validateHeaders(DataTable dataTable) {
        Map<String, Object> responseBody = ScenarioContextManager.getResponseBodyAsMap();
        @SuppressWarnings("unchecked")
        Map<String, String> responseHeadersInBody = (Map<String, String>) responseBody.get("headers");

        for (Map<String, String> row : dataTable.asMaps()) {
            String headerName = row.get("Header Name");
            String expectedValue = row.get("Expected Value");
            String actualValue;

            if ("Content-Type".equalsIgnoreCase(headerName)) {
                actualValue = ScenarioContextManager.getResponse().getHeader(headerName);
            } else {
                actualValue = responseHeadersInBody.get(headerName);
            }
            assertEquals(actualValue, expectedValue, "Header mismatch for: " + headerName);
        }
    }

    @Then("the response should contain accurate customer information")
    public void validateCustomerInformation() {
        String customerName = ScenarioContextManager.getResponse().jsonPath().getString("customer.name");
        assertEquals(customerName, "Jane Smith", "Customer name does not match");
    }

    @Then("the response should contain accurate payment details")
    public void validatePaymentDetails() {
        String paymentMethod = ScenarioContextManager.getResponse().jsonPath().getString("payment.method");
        assertEquals(paymentMethod, "Credit Card", "Payment method does not match");
    }

    @Then("the response should contain accurate product information")
    public void validateProductInformation() {
        String productName = ScenarioContextManager.getResponse().jsonPath().getString("products[0].name");
        assertEquals(productName, "Premium Product", "Product name does not match");
    }

    @Then("the response should contain {string} customer name")
    public void theResponseShouldContainCustomerName(String expectedName) throws JsonProcessingException {
        String responseString = ScenarioContextManager.getResponse().getBody().asString();
        JsonNode parsedBodyNode = mapper.readTree(responseString).path("parsedBody");
        Order order = mapper.treeToValue(parsedBodyNode, Order.class);

        assertEquals(order.getCustomer().getName(), expectedName, "Customer name does not match");
    }

    @Then("the response should contain body fields:")
    public void theResponseShouldContainBodyFields(DataTable dataTable) throws Exception {
        validateFieldsAgainstParsedBody(dataTable);
    }

    @Then("the response should contain customer information:")
    public void validateCustomerInformation(DataTable dataTable) throws Exception {
        validateFieldsAgainstParsedBody(dataTable);
    }

    @Then("the response should contain payment detail:")
    public void validatePaymentDetails(DataTable dataTable) throws Exception {
        validateFieldsAgainstParsedBody(dataTable);
    }

    @Then("the response should contain product information:")
    public void validateProductInformation(DataTable dataTable) throws Exception {
        validateFieldsAgainstParsedBody(dataTable);
    }
  

    @And("^the response should contain first details:$")
	public void theResponseShouldContainFirstDetails(DataTable dataTable) throws Exception {
        validateFieldsAgainstParsedBody(dataTable);
	}

	@And("^the response should contain second details:$")
	public void theResponseShouldContainSecondDetails(DataTable dataTable) throws Exception {
        validateFieldsAgainstParsedBody(dataTable);
	}
	
	private void validateFieldsAgainstParsedBody(DataTable dataTable) throws Exception {
	    String responseString = ScenarioContextManager.getResponse().getBody().asString();
	    JsonNode parsedBodyNode = mapper.readTree(responseString).path("parsedBody");

	    for (Map<String, String> row : dataTable.asMaps()) {
	        String fieldPath = row.get("Field Path");
	        String expectedValue = row.get("Expected Value");

	        String[] pathSegments = fieldPath.split("\\.");
	        JsonNode currentNode = parsedBodyNode;
	        for (String segment : pathSegments) {
	            if (segment.contains("[") && segment.contains("]")) {
	                // Handle array access like items[0]
	                String fieldName = segment.substring(0, segment.indexOf("["));
	                int index = Integer.parseInt(segment.substring(segment.indexOf("[") + 1, segment.indexOf("]")));
	                currentNode = currentNode.path(fieldName);
	                if (!currentNode.isArray() || currentNode.size() <= index) {
	                    throw new AssertionError("Array '" + fieldName + "' does not have index: " + index);
	                }
	                currentNode = currentNode.get(index);
	            } else {
	                currentNode = currentNode.path(segment);
	            }
	        }

	        assertEquals(currentNode.asText(), expectedValue, "Mismatch at: " + fieldPath);
	    }
	}

	}
