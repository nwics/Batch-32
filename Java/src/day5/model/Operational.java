package day5.model;

public class Operational {
    private int days;
    private double lunch;
    private double transport;
    private double totalOperational;

    public Operational(int days, double lunch, double transport) {
        this.days = days;
        this.lunch = lunch;
        this.transport = transport;
        calculateTotalOperational();

    }

    private void calculateTotalOperational() {
        this.totalOperational = getDays() * (getLunch() + getTransport());
    }

    // Getters
    public int getDays() {
        return days;
    }

    public double getLunch() {
        return lunch;
    }

    public double getTransport() {
        return transport;
    }

    public double getTotalOperational() {
        return totalOperational;
    }

}
