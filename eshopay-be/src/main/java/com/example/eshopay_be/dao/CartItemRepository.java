package com.example.eshopay_be.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eshopay_be.model.CartItem;
import com.example.eshopay_be.model.CartItemId;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {

    // CartItem findByCartItemId(Long cartItemId);
    List<CartItem> findByIdCartId(Long cartId);
}
