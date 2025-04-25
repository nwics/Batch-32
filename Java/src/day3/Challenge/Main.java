package day3.Challenge;

import java.time.LocalDate;
// import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

        public static void main(String[] args) {

                Vehicle[] vehicles = new Vehicle[10];

                vehicles[0] = new Suv("D 1001 UM", 2010, 350_000_000, 3_500_000, 4, LocalDate.of(2023, 01, 10),
                                500000, 150000);
                vehicles[1] = new Suv("D 1002 UM", 2010, 350_000_000, 3500000, 4, LocalDate.of(2023, 01, 10),
                                500000, 150000);
                vehicles[2] = new Suv("D 1003 UM", 2015, 350_000_000, 3500000, 5, LocalDate.of(2023, 1, 12),
                                500000, 150000);
                vehicles[3] = new Suv("D 1004 UM", 2015, 350_000_000, 3500000, 5, LocalDate.of(2023, 01, 13),
                                500000, 150000);
                vehicles[4] = new Taxi("D 11 UK", 2002, 1_750_000_00, 1750000, 4, LocalDate.of(2023, 01, 12),
                                45, 4500);
                vehicles[5] = new Taxi("D 12 UK", 2015, 225_000_000, 2250000, 4, LocalDate.of(2023, 01, 13),
                                75, 4500);
                vehicles[6] = new Taxi("D 13 UK", 2020, 275_000_000, 2750000, 4, LocalDate.of(2023, 01, 13),
                                90, 4500);
                vehicles[7] = new PrivetJet("ID8089", 2015, 150_000_000_000L, 1_500_000_000, 12,
                                LocalDate.of(2022, 12, 23), 55_000_000);
                vehicles[8] = new PrivetJet("ID8099", 2018, 175_000_000_000L, 1_750_000_000, 15,
                                LocalDate.of(2022, 12, 25), 75_000_000);
                vehicles[9] = new Boat("Boat18", 2020, 2_000_000_000, 20_000_000, 12,
                                LocalDate.of(2022, 12, 25), 10_000_000);

                double totalIncomeCar = 0;
                double totalIncomePlane = 0;
                double totalIncomeBoat = 0;
                double totalTax = 0;
                int totalCar = 0;
                int totalPlane = 0;
                int totalBoat = 0;
                double subTotal = 0;

                for (Vehicle vehicle : vehicles) {
                        double income = vehicle.totalIncome();
                        subTotal += income;
                        totalTax += vehicle.getTax();

                        String category = vehicle.getType().getType();

                        if (category.equals("Car")) {
                                totalIncomeCar += income;
                                totalCar++;
                        } else if (category.equals("Plane")) {
                                totalIncomePlane += income;
                                totalPlane++;
                        } else if (category.equals("Boat")) {
                                totalIncomeBoat += income;
                                totalBoat++;
                        }

                        System.out.println("No: " + vehicle.getNoPolice() +
                                        ", Type: " + vehicle.getType() +
                                        ", Category: " + category +
                                        ", Year: " + vehicle.getYear() +
                                        ", Income: " + income);
                }
                System.out.println();
                System.out.println("SubTotal: " + subTotal);
                System.out.println("Total Income Car    : " + totalIncomeCar);
                System.out.println("Total Income Plane  : " + totalIncomePlane);
                System.out.println("Total Income Boat   : " + totalIncomeBoat);
                System.out.println("Total Tax           : " + totalTax);
                System.out.println("Total Car           : " + totalCar);
                System.out.println("Total Plane         : " + totalPlane);
                System.out.println("Total Boat          : " + totalBoat);

        }

}
