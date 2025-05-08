package api.models;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class Payment {
    private String method;
    private String transaction_id;
    private ResponseAwareMatcher<Response> amount;
    private String currency;

    // Constructors
    public Payment() {
    }

    public Payment(String method, String transaction_id, 
                  ResponseAwareMatcher<Response> amount, String currency) {
        this.method = method;
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.currency = currency;
    }

    // Getters and Setters
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public ResponseAwareMatcher<Response> getAmount() {
        return amount;
    }

    public void setAmount(ResponseAwareMatcher<Response> amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

	public void setAmount(double d) {
		// TODO Auto-generated method stub
		
	}
}
