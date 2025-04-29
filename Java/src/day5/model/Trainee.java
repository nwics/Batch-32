package day5.model;

import java.util.List;

import day5.service.AllowenceService;
import day5.utils.EmployeeType;

public class Trainee extends Employee implements AllowenceService {
    private List<Allowence> allowances;
    private Operational operational;

    public Trainee(int empNo, String fullName, double salary,
            List<Allowence> allowances, Operational operational) {
        super(empNo, fullName, EmployeeType.TRAINEE, salary);
        this.allowances = allowances;
        this.operational = operational;
    }

    @Override
    public double calcTotalSalary() {
        double totalOperational = (operational != null) ? operational.getTotalOperational() : 0;

        return getSalary() + totalOperational;
    }

    // Getter for allowances
    public List<Allowence> getAllowances() {
        return allowances;
    }

    @Override
    public double getTotalAllowances() {
        double totalAllowance = 0;
        if (allowances != null) {
            for (Allowence allowance : allowances) {
                totalAllowance += allowance.getTotalAllowance();
            }
        }
        return totalAllowance;
    }

    @Override
    public String toString() {
        double totalAllowances = getTotalAllowances();
        double totalOperational = (operational != null) ? operational.getTotalOperational() : 0;

        return String.format("Employee{empId=%d, fullName='%s', hireDate=%s, salary=%.2f,roles=%s, " +
                "Total Salary=%.2f, Total Allowance=%.2f," +
                "Total Operational=%.2f, Total Tax=%.2f}",
                getEmpNo(), getFullName(), getHireDate(), getSalary(), getStatus(),
                calcTotalSalary(), totalAllowances, totalOperational, calculateTax());
    }

}
