package day4.Learn;

import java.util.List;

import java.util.stream.Collectors;

import day4.Learn.hr.Employee;
import day4.Learn.hr.Programmerr;
import day4.Learn.hr.Qa;
import day4.Learn.hr.Sales;
import day4.Learn.interfacee.IEmployee;
import day4.Learn.service.EmployeeImpl;

public class Main {

        public static void main(String[] args) {
                IEmployee iEmployee = new EmployeeImpl();
                var employee = iEmployee.initListEmployee();

                List<Employee> emp = employee.stream().filter(Employee.class::isInstance).map(Employee.class::cast)
                                .collect(Collectors.toList());
                iEmployee.generateSalary(emp);
                iEmployee.displaEmployee(emp);

        }
}
