package com.codeid.eshopper.Service;

import java.util.List;
import java.util.Optional;

import com.codeid.eshopper.model.Category;

public interface CategoryService {

    List<Category> getDataCategory();

    Category addCategory(Category category);

    Optional<Category> findCategoryById(Long categoryId);

    void deleteCategoryById(Long categoryId);

}
