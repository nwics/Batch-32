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
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public static CategoryDTO mapToDTO(Category category) {
        return new CategoryDTO(category.getCategoryId(), category.getCategoryName(), category.getDescription(),
                category.getPicture());
    }

    @Override
    public List<CategoryDTO> findAll() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'findAll'");
        log.debug("request to get data shippers");
        return this.categoryRepository.findAll().stream().map(CategoryServiceImpl::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'findById'");

        log.debug("Request to get category : {}", id);
        return this.categoryRepository.findById(id).map(CategoryServiceImpl::mapToDTO)
                .orElseThrow(() -> new EntityNotFoundException("category not found with id " + id));
    }

    @Override
    public CategoryDTO save(CategoryDTO entity) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'save'");
        log.debug("Request to create department : {}", entity);

        Category category = new Category();
        category.setCategoryName(entity.getCategoryName());
        category.setDescription(entity.getDescription());
        category.setPicture(entity.getPicture());
        return mapToDTO(this.categoryRepository.save(category));

    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO entity) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'update'");

        log.debug("Request to update Department : {}", id);

        Category category = this.categoryRepository
                .findById(id)
                .orElse(null);

        category.setCategoryName(entity.getCategoryName());
        category.setDescription(entity.getDescription());
        category.setPicture(entity.getPicture());
        this.categoryRepository.save(category);
        return mapToDTO(category);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'delete'");
        log.debug("Request to delete Department : {}", id);

        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find category with id " + id));

        this.categoryRepository.delete(category);
    }

}
