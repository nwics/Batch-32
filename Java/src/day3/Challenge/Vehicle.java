package day3.Challenge;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Vehicle {

    private String noPolice;
    private Type type;
    private int year;
    private double price;
    private double tax;
    private int seat;
    // private double totalPayment;
    private LocalDate transactionDate;

    public Vehicle(String noPolice, Type type, int year, double price, double tax, int seat,
            LocalDate transactionDate) {
        this.noPolice = noPolice;
        this.type = type;
        this.year = year;
        this.price = price;
        this.tax = tax;
        this.seat = seat;
        this.transactionDate = transactionDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNoPolice() {
        return noPolice;
    }

    public void setNoPolice(String noPolice) {
        this.noPolice = noPolice;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double totalIncome() {
        return 0.0;
    }

    // @Override
    // public String toString() {
    // return "Vehicle [noPolice=" + noPolice + ", type=" + type + ", price=" +
    // price + ", tax=" + tax + ", seat="
    // + seat + ", totalPayment=" + totalPayment + ", transactionDate=" +
    // transactionDate + "]";
    // }

}
