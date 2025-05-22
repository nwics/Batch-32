package com.example.eshopay_be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopay_be.model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, String> {

}
