package day4.Learn.hr;

import java.time.LocalDate;

import day4.Learn.Roles;
import day4.Learn.interfacee.ISalary;
import day4.Learn.salary.Transportasi;
import day4.Learn.service.SalaryImpl;

public class Programmerr extends Employee {
    private Transportasi transportasi;

    public Programmerr(int empId, String fullName, LocalDate hireDate, Roles role, double salary,
            Transportasi transportasi) {
        super(empId, fullName, hireDate, role, salary, new SalaryImpl(salary, transportasi, null, null, null));
        // setiSalary(new SalaryImpl(salary, transportasi, null, null, null));
        this.transportasi = transportasi;
    }

    public Transportasi getTransportasi() {
        return transportasi;
    }

    public void setTransportasi(Transportasi transportasi) {
        this.transportasi = transportasi;
    }

    // @Override
    // public void calculateTotalSalary() {
    // this.totalSalary = this.getSalary() + this.transportasi.getBensin() +
    // this.transportasi.getSpj()
    // + this.transportasi.getTransportasi();
    // }

}
