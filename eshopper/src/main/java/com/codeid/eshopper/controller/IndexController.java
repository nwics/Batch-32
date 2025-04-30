package com.codeid.eshopper.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeid.eshopper.model.Employee;

@Controller
@RequestMapping("index")
public class IndexController {

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("title", "Hello bootcamp");
        return "index";
    }

    @GetMapping("/employee")
    public String showEmployee(Model model) {

        List<Employee> emp = new ArrayList<>();
        emp.add(new Employee(101L, "frizz", LocalDate.of(2025, 10, 1), 5_000_000));
        emp.add(new Employee(102L, "kriss", LocalDate.of(2024, 2, 10), 3_000_000));

        model.addAttribute("title", emp);
        return "employee";
    }

}
