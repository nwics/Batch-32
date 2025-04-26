package day4.Learn;

import java.util.List;

import java.util.stream.Collectors;

import day4.Learn.hr.Programmerr;
import day4.Learn.hr.Qa;
import day4.Learn.hr.Sales;
import day4.Learn.interfacee.IEmployee;
import day4.Learn.service.EmployeeImpl;

public class Main {

    public static void main(String[] args) {
        IEmployee iEmployee = new EmployeeImpl();
        var employee = iEmployee.initListEmployee();

        // kita casting menggunakan wildcard ?, dari employee to programmer
        // metode ini lebih safe
        List<Programmerr> programmers = employee.stream().filter(Programmerr.class::isInstance)
                .map(Programmerr.class::cast).collect(Collectors.toList());
        iEmployee.generateSalary(programmers);
        iEmployee.displaEmployee(programmers);

        List<Sales> sales = employee.stream().filter(Sales.class::isInstance).map(Sales.class::cast)
                .collect(Collectors.toList());
        iEmployee.generateSalarySales(sales);
        iEmployee.displaEmployeeSales(sales);

        List<Qa> qa = employee.stream().filter(Qa.class::isInstance).map(Qa.class::cast)
                .collect(Collectors.toList());

        iEmployee.generateSalaryQa(qa);
        iEmployee.displaEmployeeQa(qa);
    }
}
