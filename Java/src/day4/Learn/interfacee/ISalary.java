package day4.Learn.interfacee;

public interface ISalary {

    public double calculateTotalSalary();

    default double calculateTax() {
        return 0.01 * calculateTotalSalary();
    }
}
