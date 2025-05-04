package com.codeid.eshopper.Service;

import java.util.List;
import java.util.Optional;

import com.codeid.eshopper.model.Products;

public interface ProductsService {

    List<Products> gettAllProducts();

    Products createProducts(Products products);

    void deleteProductsById(Long id);

    Optional<Products> findProductsById(Long id);
}
