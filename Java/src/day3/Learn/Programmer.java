package day3.Learn;

import java.time.LocalDate;

public class Programmer extends Employee {
    private double transportasi;

    public Programmer(int empId, String fullName, LocalDate hireDate, String role, double salary, double transportasi) {
        super(empId, fullName, hireDate, Roles.PROGRAMMER, salary);
        this.transportasi = transportasi;
        setTotalSalary(salary + this.transportasi);
    }

    public double getTransportasi() {
        return transportasi;
    }

    public void setTransportasi(double transportasi) {
        this.transportasi = transportasi;
        super.setTotalSalary(this.getSalary() + transportasi);
    }

    /* contoh : overloading, ga dipake */
    // public double getTunjangan(double transportasi) {
    // return this.transportasi;
    // }

    // public double getTunjangan(double transportasi, LocalDate hireDate) {
    // return transportasi;
    // }

    public LocalDate getTunjangan(LocalDate hireDate) {
        return hireDate;
    }

    public void setSalary(double updateSalary) {
        super.setSalary(super.getSalary() + updateSalary);
        super.setTotalSalary(super.getSalary() + this.transportasi);
    }

    @Override
    public void setTotalSalary(double totalSalary) {
        super.setTotalSalary(totalSalary);
    }

    @Override
    public String toString() {
        return getRole() + " = " + super.toString() +
                ", transportasi " + transportasi;
    }
}
