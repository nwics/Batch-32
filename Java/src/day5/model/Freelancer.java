package day5.model;

import day5.service.POService;
import day5.service.TaxProject;
import day5.utils.EmployeeType;

public class Freelancer extends Employee implements POService, TaxProject {
    private double po;
    private double ppn;

    public Freelancer(int empNo, String fullName, double salary,
            double po, double ppn) {
        super(empNo, fullName, EmployeeType.FREELANCER, salary);
        this.po = po;
        this.ppn = ppn;
    }

    @Override
    public double totalPO() {
        return getPo();
    }

    @Override
    public double calculateTotalSalary() {
        return totalPO() - calcPPN();
    }

    @Override
    public double calcPPN() {
        return po * 0.025;
    }

    @Override
    public double calculateTax() {
        return calcPPN();
    }

    @Override
    public String toString() {
        return String.format("Employee{empId=%d, fullName='%s', hireDate=%s, salary=%.2f,roles=%s, " +
                "Total Salary=%.2f, PO=%.2f, PPN=%.2f, Total Tax=%.2f}",
                getEmpNo(), getFullName(), getHireDate(), getSalary(), getStatus(),
                calculateTotalSalary(), po, calcPPN(), calculateTax());
    }

    // Getters
    public double getPo() {
        return po;
    }

    public double getPpn() {
        return ppn;
    }
}
