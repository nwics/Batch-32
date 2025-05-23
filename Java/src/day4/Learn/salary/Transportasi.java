package day4.Learn.salary;

import java.time.LocalDate;

import day4.Learn.interfacee.ISalary;

public class Transportasi extends Salary implements ISalary {

    private double transportasi;
    private double spj;
    private double bensin;

    public Transportasi(LocalDate payDay, double transportasi, double spj, double bensin) {
        super(payDay);
        this.transportasi = transportasi;
        this.spj = spj;
        this.bensin = bensin;
    }

    public double getTransportasi() {
        return transportasi;
    }

    public void setTransportasi(double transportasi) {
        this.transportasi = transportasi;
    }

    public double getSpj() {
        return spj;
    }

    public void setSpj(double spj) {
        this.spj = spj;
    }

    public double getBensin() {
        return bensin;
    }

    public void setBensin(double bensin) {
        this.bensin = bensin;
    }

    @Override
    public double calculateTotalSalary() {
        return spj + bensin + transportasi;
    }

}
