package com.example.eshopay_be.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopay_be.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUsersUserId(Long userId);
}
