package api.clients;

import java.util.List;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class Order {
    private String order_id;
    private Customer customer;
    private List<Item> items;
    private Payment payment;
    private Shipping shipping;
    private String order_status;
    private String created_at;

    // Default constructor
    public Order() {
    }

    // Parameterized constructor
    public Order(String order_id, Customer customer, List<Item> items, 
                Payment payment, Shipping shipping, String order_status, 
                String created_at) {
        this.order_id = order_id;
        this.customer = customer;
        this.items = items;
        this.payment = payment;
        this.shipping = shipping;
        this.order_status = order_status;
        this.created_at = created_at;
    }

    // Getters and Setters
    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String string) {
        this.order_id = string;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items2) {
        this.items = items2;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

	public String getOrderId() {
		return order_id;
	}
	
}