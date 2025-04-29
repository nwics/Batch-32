package day5.model;

import java.util.List;

import day5.service.AllowenceService;
import day5.service.TaxSalary;
import day5.utils.EmployeeType;

public class Permanent extends Employee implements AllowenceService, TaxSalary {
    private List<Allowence> allowances;
    private Insurance insurance;
    private Overtime overtime;
    private Operational operational;

    public Permanent(int empNo, String fullName, double salary,
            List<Allowence> allowances, Insurance insurance,
            Overtime overtime, Operational operational) {
        super(empNo, fullName, EmployeeType.PERMANENT, salary);
        this.allowances = allowances;
        this.insurance = insurance;
        this.overtime = overtime;
        this.operational = operational;
    }

    // Getter for allowances
    public List<Allowence> getAllowances() {
        return allowances;
    }

    @Override
    public double calcPPh() {
        return (getSalary() + getTotalAllowances()) * 0.005;
    }

    @Override
    public double calcTaperum() {
        return (getSalary() + getTotalAllowances()) * 0.005;
    }

    @Override
    public double calculateTax() {
        return calcPPh() + calcTaperum();
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
    public double calcTotalSalary() {
        double totalInsurance = (insurance != null) ? insurance.getTotalInsurance() : 0;
        double totalOvertime = (overtime != null) ? overtime.getTotalOvertime() : 0;
        double totalOperational = (operational != null) ? operational.getTotalOperational() : 0;

        return (getSalary() + totalOvertime + totalOperational) - (totalInsurance + calculateTax());
    }

    @Override
    public String toString() {
        double totalAllowances = getTotalAllowances();
        double totalInsurance = (insurance != null) ? insurance.getTotalInsurance() : 0;
        double totalOvertime = (overtime != null) ? overtime.getTotalOvertime() : 0;
        double totalOperational = (operational != null) ? operational.getTotalOperational() : 0;

        return String.format("Employee{empId=%d, fullName='%s', hireDate=%s, salary=%.2f,roles=%s,  " +
                "Total Salary=%.2f, Total Allowance=%.2f, Total Insurance=%.2f, " +
                "Total Overtime=%.2f, Total Operational=%.2f, Total Tax=%.2f}",
                getEmpNo(), getFullName(), getHireDate(), getSalary(), getStatus(),
                calcTotalSalary(), totalAllowances, totalInsurance,
                totalOvertime, totalOperational, calculateTax());
    }
}
