package com.example.eshopay_be.service;

import java.math.BigDecimal;

import com.example.eshopay_be.dto.CartDTO;

public interface CartService {

    CartDTO getCartByUserId(Long userId);

    CartDTO createCart(Long userId);

    CartDTO addItemToCart(Long cartId, Long productId, Integer quantity);

    CartDTO updateCartItem(Long cartId, Long productId, Integer quantity);

    void removeCartItem(Long cartId, Long productId);

}
