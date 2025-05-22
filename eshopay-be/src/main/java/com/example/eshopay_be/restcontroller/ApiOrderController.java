package com.example.eshopay_be.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopay_be.dto.ApiResponse;
import com.example.eshopay_be.dto.CheckoutReqDTO;
import com.example.eshopay_be.dto.OrderDTO;
import com.example.eshopay_be.service.OrderService;
import com.example.eshopay_be.service.serviceImpl.OrderServiceImpl;
import com.example.eshopay_be.util.SuccessMessage;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/order")
@RestController
@RequiredArgsConstructor
public class ApiOrderController {

    private final OrderService orderService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<ApiResponse<?>> createOrder(@PathVariable Long userId,
            @RequestBody CheckoutReqDTO checkoutReqDTO) {
        OrderDTO createOrder = orderService.createOrder(userId, checkoutReqDTO);
        ApiResponse<?> apiResponse = new ApiResponse<>(
                SuccessMessage.Order.CREATE_ORDER_USER, createOrder, LocalDateTime.now(), HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<?>> getOrderById(@PathVariable Long orderId) {
        OrderDTO order = orderService.getOrderById(orderId);
        ApiResponse<?> apiResponse = new ApiResponse<>(
                SuccessMessage.Order.GET_ORDER_ID, order, LocalDateTime.now(), HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<?>> getOrderByUserId(@PathVariable Long userId) {
        List<OrderDTO> response = orderService.getOrdersByUserId(userId);
        ApiResponse<?> apiResponse = new ApiResponse<>(
                SuccessMessage.Order.GET_ORDER_BY_USER, response, LocalDateTime.now(), HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

}
