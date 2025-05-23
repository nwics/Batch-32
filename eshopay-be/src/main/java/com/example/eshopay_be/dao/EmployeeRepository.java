package com.example.eshopay_be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopay_be.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
