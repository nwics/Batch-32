package day5.model;

import java.time.LocalDate;

public class Allowence {

    private LocalDate payDay;
    private double totalAllowance;

    public double getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(double totalAllowance) {
        this.totalAllowance = totalAllowance;
    }
}
