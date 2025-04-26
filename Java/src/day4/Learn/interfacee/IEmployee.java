package day4.Learn.interfacee;

import java.util.List;

import day4.Learn.hr.Employee;
import day4.Learn.hr.Programmerr;
import day4.Learn.hr.Qa;
import day4.Learn.hr.Sales;

public interface IEmployee {

    List<Employee> initListEmployee();

    void displaEmployee(List<Programmerr> employees);

    void generateSalary(List<Programmerr> employees);

    void displaEmployeeSales(List<Sales> sales);

    void generateSalarySales(List<Sales> sales);

    void displaEmployeeQa(List<Qa> qa);

    void generateSalaryQa(List<Qa> qa);

}
