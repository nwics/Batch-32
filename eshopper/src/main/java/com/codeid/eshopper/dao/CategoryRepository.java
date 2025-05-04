package com.codeid.eshopper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeid.eshopper.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
