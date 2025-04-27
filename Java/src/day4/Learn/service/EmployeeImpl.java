package day4.Learn.service;

import java.time.LocalDate;
import java.util.List;

// import day3.Learn.Programmer;
import day4.*;
import day4.Learn.Roles;
import day4.Learn.hr.Employee;
import day4.Learn.hr.Programmerr;
import day4.Learn.hr.Qa;
import day4.Learn.hr.Sales;
import day4.Learn.interfacee.IEmployee;
import day4.Learn.salary.Commision;
import day4.Learn.salary.Overtime;
import day4.Learn.salary.Transportasi;

public class EmployeeImpl implements IEmployee {

    @Override
    public List<Employee> initListEmployee() {
        Programmerr emp1 = new Programmerr(101, "juki", LocalDate.of(2024, 12, 12), Roles.PROGRAMMER, 5_000_000,
                new Transportasi(LocalDate.now(), 500_000, 150_000, 100_000));
        Sales emp2 = new Sales(101, "dini", LocalDate.of(2024, 1, 12), Roles.SALES, 6_000_000,
                new Commision(LocalDate.now(), 1_000_000, 1_000_000), new Overtime(LocalDate.now(), 500_000));

        Qa emp3 = new Qa(103, "rinn", LocalDate.of(2021, 10, 12), Roles.QA, 7_000_000,
                new Overtime(LocalDate.now(), 1_000_000));
        return List.of(emp1, emp2, emp3);
    }

    @Override
    public void displaEmployee(List<Employee> employees) {
        for (Employee e : employees) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void generateSalary(List<Employee> employees) {
        // double totalSalary = employee.getSalary()
        for (Employee e : employees) {
            if (e.getiSalary() != null) {
                double totalSalary = e.getiSalary().calculateTotalSalary();
                e.setTotalSalary(totalSalary);
            } else {
                e.setTotalSalary(e.getSalary());
            }
        }

    }

}
