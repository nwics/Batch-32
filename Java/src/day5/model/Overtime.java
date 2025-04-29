package day5.model;

public class Overtime {
    private double hours;
    private double uangLembur;
    private double totalOvertime;

    public Overtime(double hours, double uangLembur) {
        this.hours = hours;
        this.uangLembur = uangLembur;
        calculateTotalOvertime();
    }

    private void calculateTotalOvertime() {
        this.totalOvertime = getHours() * getUangLembur();
    }

    // Getters
    public double getHours() {
        return hours;
    }

    public double getUangLembur() {
        return uangLembur;
    }

    public double getTotalOvertime() {
        return totalOvertime;
    }
}
