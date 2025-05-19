package com.example.eshopay_be.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.eshopay_be.dao.CategoryRepository;
import com.example.eshopay_be.dto.ApiResponsePagination;
import com.example.eshopay_be.dto.CategoryDTO;
import com.example.eshopay_be.dto.Pagination;
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
    public ApiResponsePagination<CategoryDTO> findAll(Integer size, Integer current, String keyword,
            String categoryName, String sortingDirection) {

        Pageable pageable = PageRequest.of(current - 1, size, Sort.by("categoryId").ascending());
        Page<Category> pageResult = categoryRepository.findAll(pageable);
        List<CategoryDTO> categoryDTOs = pageResult.getContent().stream().map(CategoryServiceImpl::mapToDTO)
                .collect(Collectors.toList());
        Pagination pagination = new Pagination();
        pagination.setSize(size);
        pagination.setCurrent(current);
        pagination.setTotal(pageResult.getTotalElements());
        pagination.setTotalPages(pageResult.getTotalPages());

        ApiResponsePagination<CategoryDTO> response = new ApiResponsePagination<>();
        response.setMessage("success get category");
        response.setTimestamp(LocalDateTime.now());
        response.setStatusCode(200);
        response.setData(categoryDTOs);
        response.setPage(pagination);
        return response;
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

    @Override
    public void delete(Long id) {

        log.debug("Request to delete Department : {}", id);

        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find category with id " + id));

        this.categoryRepository.delete(category);
    }

}
