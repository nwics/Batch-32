package com.codeid.eshopper.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeid.eshopper.model.Category;
import com.codeid.eshopper.model.Products;
import com.codeid.eshopper.model.Suppliers;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    List<Products> findByCategoryId(Long categoryId);

    List<Products> findBySuppliers_SupplierId(Long supplierId);
    // List<Products> findBySupplierId(Long supplierId, Long id);
}
