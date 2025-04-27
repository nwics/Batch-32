package day4.Learn.hr;

import java.time.LocalDate;

import day4.Learn.Roles;
import day4.Learn.interfacee.IEmployee;
import day4.Learn.interfacee.ISalary;
import day4.Learn.salary.Overtime;
import day4.Learn.service.SalaryImpl;

public class Qa extends Employee {

    private Overtime overtime;

    public Qa(int empId, String fullName, LocalDate hireDate, Roles role, double salary, Overtime overtime) {
        super(empId, fullName, hireDate, role, salary, new SalaryImpl(salary, null, null, overtime, null));
        this.overtime = overtime;
    }

    public Overtime getOvertime() {
        return overtime;
    }

    public void setOvertime(Overtime overtime) {
        this.overtime = overtime;
    }

    // @Override
    // public void calculateTotalSalary() {
    // this.totalSalary = this.getSalary() + this.overtime.getMakan();
    // }

}
