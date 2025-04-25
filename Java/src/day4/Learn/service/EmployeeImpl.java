package day4.Learn.service;

import java.time.LocalDate;
import java.util.List;

// import day3.Learn.Programmer;
import day4.*;
import day4.Learn.Roles;
import day4.Learn.hr.Employee;
import day4.Learn.hr.Programmerr;
import day4.Learn.interfacee.IEmployee;
import day4.Learn.salary.Transportasi;

public class EmployeeImpl implements IEmployee {

    @Override
    public List<Employee> initListEmployee() {
        Programmerr emp1 = new Programmerr(101, "juki", LocalDate.of(2024, 12, 12), Roles.PROGRAMMER, 5_000_000,
                new Transportasi(LocalDate.now(), 500_000, 150_000, 100_000));
        return List.of(emp1);
    }

    @Override
    public void displaEmployee(List<Programmerr> employees) {
        for (Employee e : employees) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void generateSalary(List<Programmerr> employees) {
        for (Programmerr e : employees) {
            e.calculateTotalSalary();
        }

    }

}
