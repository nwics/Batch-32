package com.example.eshopay_be.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopay_be.model.ProductImages;
import com.example.eshopay_be.model.Products;

@Repository
public interface ProductPhotoRepository extends JpaRepository<ProductImages, Long> {

    List<ProductImages> findByProducts(Products products);

}
