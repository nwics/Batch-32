package com.example.eshopay_be.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.eshopay_be.dao.CategoryRepository;
import com.example.eshopay_be.dto.CategoryDTO;
import com.example.eshopay_be.dto.ShippersDTO;
import com.example.eshopay_be.model.Category;
import com.example.eshopay_be.model.Shippers;
import com.example.eshopay_be.model.Suppliers;
import com.example.eshopay_be.service.CategoryService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public static CategoryDTO mapToDTO(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDTO(category.getCategoryId(), category.getCategoryName(), category.getDescription(),
                category.getPicture());
    }

    public static Category mapToModel(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        return new Category(categoryDTO.getCategoryId(), categoryDTO.getCategoryName(), categoryDTO.getDescription(),
                categoryDTO.getPicture());
    }

    @Override
    public List<CategoryDTO> findAll() {

        log.debug("request to get data shippers");
        return this.categoryRepository.findAll().stream().map(CategoryServiceImpl::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {

        log.debug("Request to get category : {}", id);
        return this.categoryRepository.findById(id).map(CategoryServiceImpl::mapToDTO)
                .orElseThrow(() -> new EntityNotFoundException("category not found with id " + id));
    }

    @Override
    public CategoryDTO save(CategoryDTO entity) {

        log.debug("Request to create department : {}", entity);

        Category category = new Category();
        category.setCategoryName(entity.getCategoryName());
        category.setDescription(entity.getDescription());
        category.setPicture(entity.getPicture());
        return mapToDTO(this.categoryRepository.save(category));

    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO entity) {

        // log.debug("Request to update Department : {}", id);

        Category category = this.categoryRepository
                .findById(id)
                .orElse(null);

        category.setCategoryName(entity.getCategoryName());
        category.setDescription(entity.getDescription());
        category.setPicture(entity.getPicture());
        this.categoryRepository.save(category);
        return mapToDTO(category);
    }

    // debuggin punya orang
    // @Override
    // public CategoryDTO update(Long id, CategoryDTO entity) {
    // var category = this.categoryRepository
    // .findById(id)
    // .orElse(null);
    // category.setCategoryName(entity.getCategoryName());
    // category.setDescription(entity.getDescription());
    // this.categoryRepository.save(category);
    // return mapToDTO(category);
    // }

    @Override
    public void delete(Long id) {

        log.debug("Request to delete Department : {}", id);

        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find category with id " + id));

        this.categoryRepository.delete(category);
    }

}
