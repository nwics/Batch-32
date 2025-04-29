package day5.model;

import java.util.List;

import day5.service.AllowenceService;
import day5.service.TaxSalary;
import day5.utils.EmployeeType;

public class Contract extends Employee implements AllowenceService, TaxSalary {
    // private List<Allowance> allowances;
    private List<Allowence> allowences;
    private Insurance insurance;
    private Overtime overtime;
    private Operational operational;

    public Contract(int empNo, String fullName, double salary,
            List<Allowence> allowances, Insurance insurance,
            Overtime overtime, Operational operational) {
        super(empNo, fullName, EmployeeType.CONTRACT, salary);
        this.allowences = allowances;
        this.insurance = insurance;
        this.overtime = overtime;
        this.operational = operational;
    }

    @Override
    public double calcTotalSalary() {
        double totalInsurance = (insurance != null) ? insurance.getTotalInsurance() : 0;
        double totalOvertime = (overtime != null) ? overtime.getTotalOvertime() : 0;
        double totalOperational = (operational != null) ? operational.getTotalOperational() : 0;

        return (getSalary() + totalOvertime + totalOperational) - (totalInsurance + calculateTax());
    }

    public List<Allowence> getAllowances() {
        return allowences;
    }

    @Override
    public double getTotalAllowances() {
        double totalAllowance = 0;
        if (allowences != null) {
            for (Allowence allowance : allowences) {
                totalAllowance += allowance.getTotalAllowance();
            }
        }
        return totalAllowance;
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
    public String toString() {
        double totalAllowances = getTotalAllowances();
        double totalInsurance = (insurance != null) ? insurance.getTotalInsurance() : 0;
        double totalOvertime = (overtime != null) ? overtime.getTotalOvertime() : 0;
        double totalOperational = (operational != null) ? operational.getTotalOperational() : 0;

        return String.format("Employee{empId=%d, fullName='%s', hireDate=%s, salary=%.2f,roles=%s, " +
                "Total Salary=%.2f, Total Allowance=%.2f, Total Insurance=%.2f, " +
                "Total Overtime=%.2f, Total Operational=%.2f, Total Tax=%.2f}",
                getEmpNo(), getFullName(), getHireDate(), getSalary(), getStatus(),
                calcTotalSalary(), totalAllowances, totalInsurance,
                totalOvertime, totalOperational, calculateTax());
    }
}