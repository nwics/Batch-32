package com.example.eshopay_be.restcontroller;

import com.example.eshopay_be.dto.ApiResponse;

// import org.springframework.web.bind.annotation.RestController;

// import com.example.eshopay_be.dto.SupplierDTO;
import com.example.eshopay_be.service.BaseService;
// import com.example.eshopay_be.service.SupplierService;

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

import jakarta.validation.Valid;

public abstract class ApiBaseController<T, ID> {

    protected abstract BaseService<T, ID> getService();

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<T>>> getAll() {
        ApiResponse<List<T>> apiResponse = new ApiResponse<>(
                "susccess get data", getService().findAll(), LocalDateTime.now(), HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
        // return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<T>> getById(@PathVariable ID id) {
        ApiResponse<T> apiResponse = new ApiResponse<>(
                "success get data id ", getService().findById(id), LocalDateTime.now(), HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<T>> create(@RequestBody @Valid T entity) {
        ApiResponse<T> apiResponse = new ApiResponse<>(
                "success create data", getService().save(entity), LocalDateTime.now(), HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<T>> update(@PathVariable ID id, @RequestBody @Valid T entity) {
        ApiResponse<T> apiResponse = new ApiResponse<>(
                "success update data", getService().update(id, entity), LocalDateTime.now(), HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable ID id) {
        getService().delete(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                "success delete data", null, LocalDateTime.now(), HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
        // getService().delete(id);
        // return ResponseEntity.noContent().build();
    }
}
