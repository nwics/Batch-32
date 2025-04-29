package day5.service;

import java.util.List;

import day5.model.Employee;
import day5.utils.EmployeeType;

public interface HRService {
    List<Employee> initEmployeeData();

    Employee getTotalEmployee(List<Employee> emps);

    Employee getTotalEmployeeByType(List<Employee> emps, EmployeeType empType);

    void displaEmployee(List<Employee> employees);
}
