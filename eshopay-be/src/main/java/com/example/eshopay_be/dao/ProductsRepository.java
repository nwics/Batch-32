package com.example.eshopay_be.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eshopay_be.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {

}
