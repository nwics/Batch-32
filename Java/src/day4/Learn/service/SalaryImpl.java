package day4.Learn.service;

import day4.Learn.interfacee.ISalary;
import day4.Learn.salary.Commision;
import day4.Learn.salary.Medical;
import day4.Learn.salary.Overtime;
import day4.Learn.salary.Transportasi;

public class SalaryImpl implements ISalary {

    private double salary;
    private Transportasi transportasi;
    private Medical medical;
    private Overtime overtime;
    private Commision commision;

    public SalaryImpl(double salary, Transportasi transportasi, Medical medical, Overtime overtime,
            Commision commision) {
        this.salary = salary;
        this.transportasi = transportasi;
        this.medical = medical;
        this.overtime = overtime;
        this.commision = commision;
    }

    @Override
    public double calculateTotalSalary() {

        double total = salary;
        if (overtime != null) {
            total += overtime.getMakan();
        }
        if (commision != null) {
            total += commision.getBonus() + commision.getCommision();
        }
        if (transportasi != null) {
            total += transportasi.getBensin() + transportasi.getSpj() + transportasi.getTransportasi();
        }
        if (medical != null) {
            total += medical.getKacamata();
        }

        return total;

    }

}
