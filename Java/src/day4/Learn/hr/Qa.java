package day4.Learn.hr;

import java.time.LocalDate;

import day4.Learn.Roles;
import day4.Learn.interfacee.IEmployee;
import day4.Learn.interfacee.ISalary;
import day4.Learn.salary.Overtime;

public class Qa extends Employee implements ISalary {

    private Overtime overtime;

    public Qa(int empId, String fullName, LocalDate hireDate, Roles role, double salary, Overtime overtime) {
        super(empId, fullName, hireDate, role, salary);
        this.overtime = overtime;
    }

    @Override
    public void calculateTotalSalary() {
        setTotalSalary(getSalary() + overtime.getMakan());
    }

}
