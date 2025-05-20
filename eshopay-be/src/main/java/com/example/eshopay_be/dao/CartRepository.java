package com.example.eshopay_be.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eshopay_be.model.Carts;

public interface CartRepository extends JpaRepository<Carts, Long> {

    Carts findByUserId(Long userId);

    Carts findByCartId(Long cartId);
}
