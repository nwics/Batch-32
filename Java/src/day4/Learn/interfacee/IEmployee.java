package day4.Learn.interfacee;

import java.util.List;

import day4.Learn.hr.Employee;
import day4.Learn.hr.Programmerr;
import day4.Learn.hr.Qa;
import day4.Learn.hr.Sales;

public interface IEmployee {

    List<Employee> initListEmployee();

    void displaEmployee(List<Employee> employees);

    void generateSalary(List<Employee> employees);

}
