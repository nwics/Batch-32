package com.codeid.eshopper.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeid.eshopper.Service.ProductsService;
import com.codeid.eshopper.dao.ProductsRepository;
import com.codeid.eshopper.model.Products;

@Service
public class ProductServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    public ProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Products> gettAllProducts() {

        return productsRepository.findAll();
    }

    @Override
    public Products createProducts(Products products) {
        return productsRepository.save(products);
    }

    @Override
    public void deleteProductsById(Long id) {

        Products products = productsRepository.findById(id).orElse(null);
        if (products != null) {

            productsRepository.delete(products);
        }
    }

    @Override
    public Optional<Products> findProductsById(Long id) {

        // Products products = productsRepository.findById(id).orElse(null);
        return productsRepository.findById(id);
    }

}
