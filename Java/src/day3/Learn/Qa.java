package day3.Learn;

import java.time.LocalDate;

import javax.management.relation.Role;

public class Qa extends Employee {

    private double eatAllowence;

    public Qa(int empId, String fullName, LocalDate hireDate, String role, double salary, double eatAllowence) {
        super(empId, fullName, hireDate, Roles.QA, salary);
        this.eatAllowence = eatAllowence;
        setTotalSalary(salary + eatAllowence);
    }

    public double getEatAllowence() {
        return eatAllowence;
    }

    public void setEatAllowence(double eatAllowence) {
        this.eatAllowence = eatAllowence;
    }

    @Override
    public String toString() {
        return getRole() + " = " + super.toString() + " [eatAllowence=" + eatAllowence + "]";
    }

}
