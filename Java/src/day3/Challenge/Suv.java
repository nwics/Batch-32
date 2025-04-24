package day3.Challenge;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Suv extends Cars {
    private double rent;
    private double driver;

    public Suv(String noPolice, int year, double price, double tax, int seat, LocalDate transactionDate,
            double rent, double driver) {
        super(noPolice, Type.SUV, year, price, tax, seat, transactionDate);
        this.rent = rent;
        this.driver = driver;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public double getDriver() {
        return driver;
    }

    public void setDriver(double driver) {
        this.driver = driver;
    }

    public double totalIncome() {
        return rent + driver;
    }

}
