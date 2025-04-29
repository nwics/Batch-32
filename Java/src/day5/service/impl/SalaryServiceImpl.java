package day5.service.impl;

import java.util.List;

import day5.model.Employee;
import day5.service.AllowenceService;
import day5.service.SalaryService;
import day5.utils.EmployeeType;

public class SalaryServiceImpl implements SalaryService {

    @Override
    public double generateSalary(List<Employee> emps) {
        double totalSalary = 0;
        for (Employee emp : emps) {
            totalSalary += emp.getSalary();
        }
        return totalSalary;
    }

    @Override
    public double getTotalAllowances(List<Employee> emps) {
        double totalAllowances = 0;
        for (Employee emp : emps) {
            if (emp instanceof AllowenceService) {
                AllowenceService allowenceService = (AllowenceService) emp;
                totalAllowances += (allowenceService.calcTotalSalary() - emp.getSalary());
            }

        }
        return totalAllowances;
    }

    @Override
    public double getTotalSalaryByType(List<Employee> emps, EmployeeType empType) {
        double totalSalary = 0;
        for (Employee emp : emps) {
            if (emp.getStatus() == empType) {
                totalSalary += emp.getSalary();
                if (emp instanceof AllowenceService) {
                    AllowenceService allowenceService = (AllowenceService) emp;
                    totalSalary += (allowenceService.calcTotalSalary() - emp.getSalary());
                }

            }
        }
        return totalSalary;
    }

}
