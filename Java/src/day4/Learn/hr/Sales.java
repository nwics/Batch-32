package day4.Learn.hr;

import java.time.LocalDate;

import javax.management.relation.Role;

import day4.Learn.Roles;
import day4.Learn.interfacee.ISalary;
import day4.Learn.salary.Commision;
import day4.Learn.salary.Overtime;

public class Sales extends Employee implements ISalary {

    private Commision commision;
    private Overtime overtime;

    public Sales(int empId, String fullName, LocalDate hireDate, Roles role, double salary, Commision commision,
            Overtime overtime) {
        super(empId, fullName, hireDate, role, salary);
        this.commision = commision;
        this.overtime = overtime;
    }

    @Override
    public void calculateTotalSalary() {
        setTotalSalary(getSalary() + commision.getCommision() + commision.getBonus() + overtime.getMakan());
    }

}
