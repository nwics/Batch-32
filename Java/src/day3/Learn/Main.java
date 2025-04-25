package day3.Learn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

public class Main {

    // Employee
    public static void main(String[] args) {
        // implement encapsulation
        Employee emp1 = new Employee(); // default/empty constructor
        emp1.setEmpId(101);
        emp1.setFullName("Anton");
        emp1.setHireDate(LocalDate.of(2025, 4, 12));
        emp1.setRole("Programmer");
        emp1.setSalary(6_000_000);
        emp1.setTotalSalary(emp1.getSalary());

        // Programmer prog1 = new Programmer(101, "tio", LocalDate.of(2025, 10, 11),
        // "Programmer", 5_000_000, 1_000_000);
        // prog1.setSalary(1_000_000);
        // prog1.setFullName(" jumawa");

        Employee prog2 = new Programmer(102, "juju", LocalDate.of(2025, 12, 11), "Programmer", 6_000_000, 500_000);
        Sales sales1 = new Sales(103, "Jodi", LocalDate.of(2024, 11, 12), "Sales", 9_000_000, 500_000, 600_000);
        Qa qa1 = new Qa(104, "Joko", LocalDate.of(2023, 11, 12), "QA", 7_000_000, 1_000_000);

        List<Employee> listEmp = new ArrayList<>();
        listEmp.add(emp1);
        // listEmp.add(prog1);
        listEmp.add(prog2);
        listEmp.add(sales1);
        listEmp.add(qa1);

        System.out.println();
        for (Employee e : listEmp) {
            System.out.println(e);
        }
        System.out.println("Total Employe : " + Employee.totalEmployee);
        System.out.println();
    }
}
