package com.codeid.eshopper.model;

import java.time.LocalDate;

public class Employee {
    private Long empId;
    private String name;
    private LocalDate hireDate;
    private double salary;

    public Employee(Long empId, String name, LocalDate hireDate, double salary) {
        this.empId = empId;
        this.name = name;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Constructor

}