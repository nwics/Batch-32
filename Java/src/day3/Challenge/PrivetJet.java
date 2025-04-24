package day3.Challenge;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PrivetJet extends Vehicle {

    private double orderPerHours;

    public PrivetJet(String noPolice, int year, double price, double tax, int seat,
            LocalDate transactionDate, double orderPerHours) {
        super(noPolice, Type.PRIVATE_JET, year, price, tax, seat, transactionDate);
        this.orderPerHours = orderPerHours;
    }

    public double getOrderPerHours() {
        return orderPerHours;
    }

    public void setOrderPerHours(double orderPerHours) {
        this.orderPerHours = orderPerHours;
    }

    public double totalIncome() {
        return orderPerHours;
    }

}
