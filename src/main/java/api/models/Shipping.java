package api.models;


public class Shipping {
    private String method;
    private double cost;
    private String estimated_delivery;

    // Constructors
    public Shipping() {
    }

    public Shipping(String method, double cost, String estimated_delivery) {
        this.method = method;
        this.cost = cost;
        this.estimated_delivery = estimated_delivery;
    }

    // Getters and Setters
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getEstimated_delivery() {
        return estimated_delivery;
    }

    public void setEstimated_delivery(String estimated_delivery) {
        this.estimated_delivery = estimated_delivery;
    }
}
