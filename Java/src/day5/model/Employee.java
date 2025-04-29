package day5.model;

import java.time.LocalDate;

import day5.utils.EmployeeType;

public class Employee {
    private int empNo;
    private String fullName;
    private EmployeeType status;
    private LocalDate hireDate;
    private double salary;
    private double totalSalary;

    public Employee(int empNo, String fullName, EmployeeType status, double salary) {
        this.empNo = empNo;
        this.fullName = fullName;
        this.status = status;
        this.salary = salary;
    }

    @Override
    public String toString() {
        // double totalTax = (iSalary != null ? iSalary.calculateTax() : 0);
        double totalTax = 0.0;
        return "Employee{" +
                "empId=" + empNo +
                ", fullName='" + fullName + '\'' +
                ", hireDate=" + hireDate +
                ", role=" + status +
                ", salary=" + salary +
                ", Total Salary=" + totalSalary +
                ", Total Tax=" + totalTax +
                '}';
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    // Getters and setters
    public int getEmpNo() {
        return empNo;
    }

    public String getFullName() {
        return fullName;
    }

    public EmployeeType getStatus() {
        return status;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double calculateTotalSalary() {
        return salary;
    }

    public double calculateTax() {
        return calculateTotalSalary() * 0.01;
    }

}
