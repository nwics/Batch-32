package day3.Challenge;

import java.time.LocalDate;

public class Cars extends Vehicle {

    public Cars(String noPolice, Type type, int year, double price,
            double taxPerYear, int seat, LocalDate transactionDate) {
        super(noPolice, type, year, price, taxPerYear, seat, transactionDate);
    }

}
