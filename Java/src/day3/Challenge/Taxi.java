package day3.Challenge;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Taxi extends Cars {

    private int order;
    private double orderPerKM;

    public Taxi(String noPolice, int year, double price, double tax, int seat, LocalDate transactionDate,
            int order, double orderPerKM) {
        super(noPolice, Type.TAXI, year, price, tax, seat, transactionDate);
        this.order = order;
        this.orderPerKM = orderPerKM;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public double getOrderPerKM() {
        return orderPerKM;
    }

    public void setOrderPerKM(double orderPerKM) {
        this.orderPerKM = orderPerKM;
    }

    public double totalIncome() {
        return order * orderPerKM;
    }

}
