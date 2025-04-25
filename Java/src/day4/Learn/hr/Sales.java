package day4.Learn.hr;

import java.time.LocalDate;

import javax.management.relation.Role;

import day4.Learn.Roles;

public class Sales extends Employee {

    private double bonus;
    private double commision;

    public Sales(int empId, String fullName, LocalDate hireDate, String role, double salary, double bonus,
            double commision) {
        super(empId, fullName, hireDate, Roles.SALES, salary);
        this.bonus = bonus;
        this.commision = commision;
        setTotalSalary(salary + bonus + commision);
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
    public String toString() {
        return getRole() + " = " + super.toString() + ", {bonus=" + bonus + ", commision=" + commision + "}";
    }

}
