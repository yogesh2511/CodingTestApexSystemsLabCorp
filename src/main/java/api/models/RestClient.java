package api.models;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	private static RequestSpecification request;
	private static Response response;
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static String requestBody;

	// Reset and start a fresh request
	public static RestClient reset() {
		RestAssured.baseURI = "https://echo.free.beeceptor.com"; // ✅ load base URI here
		request = RestAssured.given().log().all();
		return new RestClient();
	}

	public RestClient withHeaders(Map<String, String> headers) {
		request.headers(headers);
		return this;
	}

	public RestClient withQueryParams(Map<String, String> params) {
		request.queryParams(params);
		return this;
	}

	public static Response get(String endpoint) {
		if (request == null) {
			reset();
		}
		response = request.get(endpoint)
				.then()
				.extract().response();
		 response.getBody().asString();
		    return response;
	}

	public static Response post(String endpoint, Object requestBody) {
		try {
			request.contentType(ContentType.JSON);

			if (requestBody != null) {
				String finalBody = requestBody instanceof String ? (String) requestBody
						: objectMapper.writeValueAsString(requestBody);

				request.body(finalBody);
			}

			response = request.post(endpoint);
			return response;

		} catch (Exception e) {
			throw new RuntimeException("Failed to send POST request", e);
		}
	}

	public static Response post(String endpoint) {
		return post(endpoint, null);
	}

	public static Response put(String endpoint, Object requestBody) {
		request.contentType(ContentType.JSON);

		if (requestBody != null) {
			request.body(requestBody);
		}

		response = request.put(endpoint);
		return response;
	}

	public static Response patch(String endpoint, Object requestBody) {
		request.contentType(ContentType.JSON);

		if (requestBody != null) {
			request.body(requestBody);
		}

		response = request.patch(endpoint);
		return response;
	}

	public static Response delete(String endpoint) {
		response = request.delete(endpoint);
		return response;
	}

	public static Response getResponse() {
		return response;
	}

	public static void setRequestBody(String body) {
		requestBody = body;
	}

	public static String getRequestBody() {
		return requestBody;
	}

	// --- Utility Methods ---

	// Get HTTP Status Code
	public static int getStatusCode() {
	    if (response == null) {
	        throw new IllegalStateException("Response is null. Send a request first.");
	    }
	    return response.getStatusCode();
	}

	// Get HTTP Status Line
	public static String getStatusLine() {
	    if (response == null) {
	        throw new IllegalStateException("Response is null. Send a request first.");
	    }
	    return response.getStatusLine();
	}

	// Get Response Body as String
	public static String getResponseBodyAsString() {
	    if (response == null) {
	        throw new IllegalStateException("Response is null. Send a request first.");
	    }
	    return response.getBody().asString();
	}

	// Get Response Body as JSON Path (to query fields easily)
	public static io.restassured.path.json.JsonPath getResponseJsonPath() {
	    if (response == null) {
	        throw new IllegalStateException("Response is null. Send a request first.");
	    }
	    return response.jsonPath();
	}

	// Get a Header Value
	public static String getHeader(String headerName) {
	    if (response == null) {
	        throw new IllegalStateException("Response is null. Send a request first.");
	    }
	    return response.getHeader(headerName);
	}

	// Validate Response Status Code (assertion helper)
	public static boolean isStatusCode(int expectedStatusCode) {
	    return getStatusCode() == expectedStatusCode;
	}

	// Validate Response Header Exists
	public static boolean hasHeader(String headerName) {
	    if (response == null) {
	        throw new IllegalStateException("Response is null. Send a request first.");
	    }
	    return response.getHeaders().hasHeaderWithName(headerName);
	}

}
