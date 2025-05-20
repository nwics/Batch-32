package com.example.eshopay_be.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopay_be.dto.ApiResponse;
import com.example.eshopay_be.dto.CartDTO;
import com.example.eshopay_be.service.CartService;
import com.example.eshopay_be.service.serviceImpl.CartServiceImpl;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class ApiCartController {

        // private final CartServiceImpl cartServiceImpl;
        private final CartService cartService;

        @GetMapping("/{userId}")
        public ResponseEntity<ApiResponse<?>> getCatById(@PathVariable Long userId) {

                CartDTO response = cartService.getCartByUserId(userId);
                ApiResponse<?> apiResponse = new ApiResponse<>(
                                "success get cart id", response, LocalDateTime.now(), HttpStatus.OK.value());

                return ResponseEntity.ok(apiResponse);
        }

        @PostMapping("/create/{userId}")
        public ResponseEntity<ApiResponse<?>> addCart(@PathVariable Long userId) {
                CartDTO response = cartService.createCart(userId);
                ApiResponse<?> apiResponse = new ApiResponse<>(
                                "success add cart by userId", response, LocalDateTime.now(), HttpStatus.OK.value());
                return ResponseEntity.ok(apiResponse);
        }

        @PostMapping("/{cartId}/add")
        public ResponseEntity<ApiResponse<?>> addItemToCart(@PathVariable Long cartId, @RequestParam Long productId,
                        @RequestParam Integer quantity) {

                CartDTO response = cartService.addItemToCart(cartId, productId, quantity);
                ApiResponse<?> apiResponse = new ApiResponse<>(
                                "success add item to cart", response, LocalDateTime.now(), HttpStatus.OK.value());
                return ResponseEntity.ok(apiResponse);

        }

        @PutMapping("/{cartId}/{productId}/update")
        public ResponseEntity<ApiResponse<?>> updateCartItem(
                        @PathVariable Long cartId,
                        @PathVariable Long productId,
                        @RequestParam Integer quantity) {

                CartDTO response = cartService.updateCartItem(cartId, productId, quantity);

                ApiResponse<?> apiResponse = new ApiResponse<>(
                                "success update cart item", response, LocalDateTime.now(), HttpStatus.OK.value());

                return ResponseEntity.ok(apiResponse);
        }

        @DeleteMapping("/{cartId}/{productId}/remove")
        public ResponseEntity<ApiResponse<?>> removeCartItem(
                        @PathVariable Long cartId,
                        @PathVariable Long productId, @PathVariable Integer quantity) {

                cartService.removeCart(cartId, productId, quantity);

                ApiResponse<?> apiResponse = new ApiResponse<>(
                                "success remove cart item", null, LocalDateTime.now(), HttpStatus.OK.value());

                return ResponseEntity.ok(apiResponse);
        }

        @PutMapping("/{cartId}/clean")
        public ResponseEntity<ApiResponse<?>> cleanCart(@PathVariable Long cartId, @RequestParam Long productId) {
                cartService.cleanCart(cartId, productId);

                ApiResponse<?> apiResponse = new ApiResponse<>(
                                "success clean cart", null, LocalDateTime.now(), HttpStatus.OK.value());
                return ResponseEntity.ok(apiResponse);

        }
}
