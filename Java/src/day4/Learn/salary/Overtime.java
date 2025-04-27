package day4.Learn.salary;

import java.time.LocalDate;

import day4.Learn.interfacee.ISalary;

public class Overtime extends Salary implements ISalary {

    private double makan;

    public Overtime(LocalDate payDay, double makan) {
        super(payDay);
        this.makan = makan;
    }

    public double getMakan() {
        return makan;
    }

    public void setMakan(double makan) {
        this.makan = makan;
    }

    @Override
    public double calculateTotalSalary() {
        return makan;

    }

}
