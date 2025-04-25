package day4.Learn.hr;

import java.time.LocalDate;

import day4.Learn.Roles;
import day4.Learn.interfacee.ISalary;
import day4.Learn.salary.Transportasi;

public class Programmerr extends Employee implements ISalary {
    private Transportasi transportasi;

    public Programmerr(int empId, String fullName, LocalDate hireDate, Roles role, double salary,
            Transportasi transportasi) {
        super(empId, fullName, hireDate, role, salary);
        this.transportasi = transportasi;
    }

    @Override
    public void calculateTotalSalary() {
        setTotalSalary(getSalary() + transportasi.getBensin() + transportasi.getSpj() + transportasi.getTransportasi());
    }

}
