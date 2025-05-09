package com.example.eshopay_be.dao;

// import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopay_be.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
