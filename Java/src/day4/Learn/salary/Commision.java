package day4.Learn.salary;

import java.time.LocalDate;

import day4.Learn.interfacee.ISalary;

public class Commision extends Salary implements ISalary {

    private double bonus;
    private double commision;

    public Commision(LocalDate payDay, double bonus, double commision) {
        super(payDay);
        this.bonus = bonus;
        this.commision = commision;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getCommision() {
        return commision;
    }

    public void setCommision(double commision) {
        this.commision = commision;
    }

    @Override
    public double calculateTotalSalary() {
        return commision + bonus;
    }

    // @Override
    // public double calculateTotalSalary() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'calculateTotalSalary'");
    // }

}
