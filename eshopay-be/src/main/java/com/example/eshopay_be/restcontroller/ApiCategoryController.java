package com.example.eshopay_be.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopay_be.dto.ApiResponse;
import com.example.eshopay_be.dto.CategoryDTO;
import com.example.eshopay_be.dto.ShippersDTO;
import com.example.eshopay_be.service.BaseService;
import com.example.eshopay_be.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class ApiCategoryController extends ApiBaseController<CategoryDTO, Long> {

    private final CategoryService categoryService;

    @Override
    protected BaseService<CategoryDTO, Long> getService() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getService'");
        return categoryService;
    }

    @Override
    public ResponseEntity<ApiResponse<CategoryDTO>> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        return super.create(categoryDTO);
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<ApiResponse<CategoryDTO>> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<ApiResponse<CategoryDTO>> update(Long id, @Valid CategoryDTO entity) {
        return super.update(id, entity);
    }

}
