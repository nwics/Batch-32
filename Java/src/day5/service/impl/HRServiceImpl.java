package day5.service.impl;

import java.util.ArrayList;
import java.util.List;

import day5.model.Contract;
import day5.model.Employee;
import day5.model.Freelancer;
import day5.model.Insurance;
import day5.model.Operational;
import day5.model.Overtime;
import day5.model.Permanent;
import day5.model.Trainee;
import day5.service.HRService;
import day5.utils.EmployeeType;

public class HRServiceImpl implements HRService {

    @Override
    public List<Employee> initEmployeeData() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Permanent(1, "Anton", 20000000, new ArrayList<>(), new Insurance(1, 2, 0.02, 20_000_000),
                new Overtime(10, 50_000), null));
        employees.add(new Permanent(2, "Budi", 15000000, new ArrayList<>(), new Insurance(1, 3, 0.02, 15_000_000),
                new Overtime(5, 50_000), null));
        employees.add(new Contract(3, "Charlie", 15000000, new ArrayList<>(), new Insurance(1, 0, 0.02, 15_000_000),
                new Overtime(5, 45_000), null));
        employees.add(new Contract(4, "Dian", 10000000, new ArrayList<>(), new Insurance(1, 0, 0.02, 10_000_000),
                new Overtime(6, 45_000), null));
        employees.add(new Trainee(5, "Gita", 0, new ArrayList<>(), new Operational(25, 30_000, 20_000)));
        employees.add(new Freelancer(6, "Rima", 0, 25_000_000, 2.5));

        return employees;
    }

    @Override
    public Employee getTotalEmployee(List<Employee> emps) {

        return emps.isEmpty() ? null : emps.get(0);
    }

    @Override
    public Employee getTotalEmployeeByType(List<Employee> emps, EmployeeType empType) {
        for (Employee emp : emps) {
            if (emp.getStatus() == empType) {
                return emp;
            }
        }
        return null;
    }

    @Override
    public void displaEmployee(List<Employee> employees) {

        for (Employee e : employees) {
            System.out.println(e.toString());
        }
    }

}
