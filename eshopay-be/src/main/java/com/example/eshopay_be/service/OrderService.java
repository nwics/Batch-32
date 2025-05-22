package com.example.eshopay_be.service;

import java.util.List;

import com.example.eshopay_be.dto.CheckoutReqDTO;
import com.example.eshopay_be.dto.OrderDTO;

public interface OrderService {
    OrderDTO createOrder(Long userId, CheckoutReqDTO checkoutReqDTO);

    OrderDTO getOrderById(Long orderId);

    List<OrderDTO> getOrdersByUserId(Long userId);
}
