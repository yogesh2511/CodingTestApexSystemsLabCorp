package api.services;

import api.clients.Order;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class OrderService {

	public static Response postOrder(String endpoint, Order order) {
		Response response = RestAssured.given().contentType("application/json").body(order).when().post(endpoint);
		return response;
	}

}
