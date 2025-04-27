package day4.Learn.hr;

import java.time.LocalDate;

import javax.management.relation.Role;

import day4.Learn.Roles;
import day4.Learn.interfacee.ISalary;
import day4.Learn.salary.Commision;
import day4.Learn.salary.Overtime;
import day4.Learn.service.SalaryImpl;

public class Sales extends Employee {

    private Commision commision;
    private Overtime overtime;

    public Sales(int empId, String fullName, LocalDate hireDate, Roles role, double salary, Commision commision,
            Overtime overtime) {
        super(empId, fullName, hireDate, role, salary, new SalaryImpl(salary, null, null, overtime, commision));
        this.commision = commision;
        this.overtime = overtime;
    }

    public Commision getCommision() {
        return commision;
    }

    public void setCommision(Commision commision) {
        this.commision = commision;
    }

    public Overtime getOvertime() {
        return overtime;
    }

    public void setOvertime(Overtime overtime) {
        this.overtime = overtime;
    }

    // @Override
    // public void calculateTotalSalary() {
    // this.totalSalary = this.getSalary() + this.commision.getCommision() +
    // this.overtime.getMakan()
    // + this.commision.getBonus();
    // }

}
