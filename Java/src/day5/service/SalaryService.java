package day5.service;

import java.util.List;

import day5.model.Employee;
import day5.utils.EmployeeType;

public interface SalaryService {
    double generateSalary(List<Employee> emps);

    double getTotalAllowances(List<Employee> emps);

    double getTotalSalaryByType(List<Employee> emps, EmployeeType empType);

}
