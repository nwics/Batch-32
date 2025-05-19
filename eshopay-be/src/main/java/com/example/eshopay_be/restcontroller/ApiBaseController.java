package com.example.eshopay_be.restcontroller;

import com.example.eshopay_be.dto.ApiResponse;
import com.example.eshopay_be.dto.ApiResponsePagination;

// import org.springframework.web.bind.annotation.RestController;

// import com.example.eshopay_be.dto.SupplierDTO;
import com.example.eshopay_be.service.BaseService;
// import com.example.eshopay_be.service.SupplierService;
import com.example.eshopay_be.util.SuccessMessage;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

public abstract class ApiBaseController<T, ID> {

    protected abstract BaseService<T, ID> getService();

    @GetMapping("/")
    public ResponseEntity<ApiResponsePagination<T>> getAll(@RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "") String category,
            @RequestParam(defaultValue = "asc") String sortingDirection) {

        ApiResponsePagination<T> response = getService().findAll(size, current, keyword, category, sortingDirection);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<T>> getById(@PathVariable ID id) {
        ApiResponse<T> apiResponse = new ApiResponse<>(
                SuccessMessage.GetById.GET_BY_ID, getService().findById(id), LocalDateTime.now(),
                SuccessMessage.Http.OK);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<T>> create(@RequestBody @Valid T entity) {
        ApiResponse<T> apiResponse = new ApiResponse<>(
                SuccessMessage.Created.CREATED_DATA, getService().save(entity), LocalDateTime.now(),
                SuccessMessage.Http.OK);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<T>> update(@PathVariable ID id, @RequestBody @Valid T entity) {
        ApiResponse<T> apiResponse = new ApiResponse<>(
                SuccessMessage.Update.UPDATE_DATA, getService().update(id, entity), LocalDateTime.now(),
                SuccessMessage.Http.OK);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable ID id) {
        getService().delete(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                SuccessMessage.Delete.DELETE, null, LocalDateTime.now(), SuccessMessage.Http.OK);
        return ResponseEntity.ok(apiResponse);

    }
}
