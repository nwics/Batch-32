package day4.Learn.interfacee;

import java.util.List;

import day4.Learn.hr.Employee;
import day4.Learn.hr.Programmerr;

public interface IEmployee {

    List<Employee> initListEmployee();

    void displaEmployee(List<Programmerr> employees);

    void generateSalary(List<Programmerr> employees);
}
