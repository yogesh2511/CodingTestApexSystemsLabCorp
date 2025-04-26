package api.models;

import api.clients.Address;
import api.clients.Customer;
import api.clients.Item;
import api.clients.Order;
import api.clients.Payment;
import api.clients.Shipping;

import java.util.Arrays;
import java.util.List;

public class OrderTestBuilder {
    public Order buildDefaultOrder() {
        // Address
        Address address = new Address();
        address.setStreet("456 Oak Street");
        address.setCity("Metropolis");
        address.setState("NY");
        address.setZipcode("10001");
        address.setCountry("USA");

        // Customer
        Customer customer = new Customer();
        customer.setName("Jane Smith");
        customer.setEmail("janesmith@example.com");
        customer.setPhone("1-987-654-3210");
        customer.setAddress(address);

        // Items
        List<Item> items = Arrays.asList(
            new Item("A101", "Wireless Headphones", 1, 79.99),
            new Item("B202", "Smartphone Case", 2, 15.99)
        );

        // Payment
        Payment payment = new Payment();
        payment.setMethod("credit_card");
        payment.setTransaction_id("txn_67890");
        payment.setAmount(111.97);
        payment.setCurrency("USD");

        // Shipping
        Shipping shipping = new Shipping();
        shipping.setMethod("standard");
        shipping.setCost(5.99);
        shipping.setEstimated_delivery("2024-11-15");

        // Order
        Order order = new Order();
        order.setOrder_id("12345");
        order.setCustomer(customer);
        order.setItems(items);
        order.setPayment(payment);
        order.setShipping(shipping);
        order.setOrder_status("processing");
        order.setCreated_at("2024-11-07T12:00:00Z");

        return order;
    }
}