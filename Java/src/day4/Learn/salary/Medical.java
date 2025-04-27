package day4.Learn.salary;

import java.time.LocalDate;

import day4.Learn.interfacee.ISalary;

public class Medical extends Salary implements ISalary {

    private double kacamata;

    public Medical(LocalDate payDay, double kacamata) {
        super(payDay);
        this.kacamata = kacamata;
    }

    public double getKacamata() {
        return kacamata;
    }

    public void setKacamata(double kacamata) {
        this.kacamata = kacamata;
    }

    @Override
    public double calculateTotalSalary() {
        return kacamata;
    }

}
