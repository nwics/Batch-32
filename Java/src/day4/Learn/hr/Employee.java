package day4.Learn.hr;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import javax.management.relation.Role;

import day4.Learn.Roles;
import day4.Learn.interfacee.ISalary;
import day4.Learn.service.SalaryImpl;

// import java.sql.Date;

public class Employee {
    // attribute instance
    private int empId;
    private String fullName;
    private LocalDate hireDate;
    private Roles role;
    private double salary;

    private ISalary iSalary;
    private double totalSalary;

    public Employee(int empId, String fullName, LocalDate hireDate, Roles role, double salary, ISalary iSalary) {
        this.empId = empId;
        this.fullName = fullName;
        this.hireDate = hireDate;
        this.role = role;
        this.salary = salary;
        this.iSalary = iSalary;
    }

    @Override
    public String toString() {
        // double totalSalary = (iSalary != null ? iSalary.calculateTotalSalary() :
        double totalTax = (iSalary != null ? iSalary.calculateTax() : 0);
        // salary);
        return "Employee{" +
                "empId=" + empId +
                ", fullName='" + fullName + '\'' +
                ", hireDate=" + hireDate +
                ", role=" + role +
                ", salary=" + salary +
                ", Total Salary=" + totalSalary +
                ", Total Tax=" + totalTax +
                '}';
    }

    // public double calculateTotalSalary() {
    // return iSalary != null ? iSalary.calculateTotalSalary() : salary;
    // }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public ISalary getiSalary() {
        return iSalary;
    }

    public void setiSalary(ISalary iSalary) {
        this.iSalary = iSalary;
    }

}
