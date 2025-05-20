package com.example.eshopay_be.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopay_be.dto.ApiResponse;
import com.example.eshopay_be.dto.CheckoutReqDTO;
import com.example.eshopay_be.dto.OrderDTO;
import com.example.eshopay_be.service.OrderService;
import com.example.eshopay_be.service.serviceImpl.OrderServiceImpl;

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

    @PostMapping("/add/{cartId}")
    public ResponseEntity<ApiResponse<?>> createOrder(@PathVariable Long cartId,
            @RequestBody CheckoutReqDTO checkoutReqDTO) {
        OrderDTO createOrder = orderService.createOrder(cartId, checkoutReqDTO);
        ApiResponse<?> apiResponse = new ApiResponse<>(
                "success create orders", createOrder, LocalDateTime.now(), HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<?>> getOrderById(@PathVariable Long orderId) {
        OrderDTO order = orderService.getOrderById(orderId);
        ApiResponse<?> apiResponse = new ApiResponse<>(
                "success get order", order, LocalDateTime.now(), HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<?>> getOrderByUserId(@PathVariable Long userId) {
        List<OrderDTO> response = orderService.getOrdersByUserId(userId);
        ApiResponse<?> apiResponse = new ApiResponse<>(
                "Success get order by user", response, LocalDateTime.now(), HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

}
