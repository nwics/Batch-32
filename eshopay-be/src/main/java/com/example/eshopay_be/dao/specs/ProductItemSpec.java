package com.example.eshopay_be.dao.specs;

import org.springframework.data.jpa.domain.Specification;

import com.example.eshopay_be.model.Category;
import com.example.eshopay_be.model.Products;

import jakarta.persistence.criteria.Join;

public class ProductItemSpec {

    // Query -> select * from products where productName like='%keyword%'
    public static Specification<Products> hasKeyWordInProductName(String keyword) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("productName")), "%" + keyword.toLowerCase() + "%");
        };
    }

    public static Specification<Products> hasKeyWordInCategory(String categoryName) {
        return (root, query, criteriaBuilder) -> {
            // join with category
            Join<Products, Category> categoryJoin = root.join("category");

            // create the predicate base on category name
            return criteriaBuilder.like(
                    criteriaBuilder.lower(categoryJoin.get("categoryName")), "%" + categoryName.toLowerCase() + "%");
        };
    }

    public static Specification<Products> searchSpecification(String keyword, String category) {

        Specification<Products> specification = Specification.where(null);

        if (keyword != null && !keyword.isEmpty()) {
            specification = specification.and(hasKeyWordInProductName(keyword));
        }
        if (category != null && !category.isEmpty()) {
            specification = specification.and(hasKeyWordInCategory(category));
        }
        return specification;
    }
}
