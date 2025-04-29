import java.util.List;
import java.util.stream.Collectors;

import day4.Learn.interfacee.IEmployee;
import day4.Learn.service.EmployeeImpl;
import day5.model.Employee;
import day5.service.HRService;
import day5.service.SalaryService;
import day5.service.impl.HRServiceImpl;
import day5.service.impl.SalaryServiceImpl;
import day5.utils.EmployeeType;

public class App {
    public static void main(String[] args) throws Exception {

        HRService hrService = new HRServiceImpl();
        SalaryService salaryService = new SalaryServiceImpl();

        List<Employee> employees = hrService.initEmployeeData();

        System.out.println("Total Employees: " + employees.size());

        List<Employee> emp = employees.stream().filter(Employee.class::isInstance).map(Employee.class::cast)
                .collect(Collectors.toList());
        hrService.displaEmployee(emp);

        // IEmployee iEmployee = new EmployeeImpl();
        // var employee = iEmployee.initListEmployee();

        // List<Employee> emp =
        // employee.stream().filter(Employee.class::isInstance).map(Employee.class::cast)
        // .collect(Collectors.toList());
        // iEmployee.generateSalary(emp);
        // iEmployee.displaEmployee(emp);

        // System.out.println("\nEmployees by Type:");
        // for (EmployeeType type : EmployeeType.values()) {
        // int count = 0;
        // for (Employee emp : employees) {
        // if (emp.getStatus() == type) {
        // count++;
        // }
        // }
        // System.out.println(type + ": " + count);
        // }

        // System.out.println("\nTotal Salary by Type:");
        // for (EmployeeType type : EmployeeType.values()) {
        // System.out.println(type + ": " +
        // salaryService.getTotalSalaryByType(employees, type));
        // }
    }
}
