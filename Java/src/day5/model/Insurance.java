package day5.model;

public class Insurance {
    private int self;
    private int dependent;
    private double salary;
    private double medical;
    private double totalInsurance;

    public Insurance(int self, int dependent, double medical, double salary) {
        this.dependent = dependent;
        this.self = self;
        this.medical = medical;
        this.salary = salary;
        calculateTotalInsurance();
    }

    private void calculateTotalInsurance() {

        this.totalInsurance = (self + dependent) * medical * salary;
    }

    public int getSelf() {
        return self;
    }

    public void setSelf(int self) {
        this.self = self;
    }

    // Getters
    public int getDependent() {
        return dependent;
    }

    public double getMedical() {
        return medical;
    }

    public double getTotalInsurance() {
        return totalInsurance;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
        calculateTotalInsurance();
    }

}
