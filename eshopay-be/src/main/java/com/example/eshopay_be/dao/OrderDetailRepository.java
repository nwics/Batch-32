package com.example.eshopay_be.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopay_be.model.OrderDetails;
import com.example.eshopay_be.model.OrderDetailsId;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, OrderDetailsId> {

    List<OrderDetails> findByIdOrderId(Long orderId);
}
