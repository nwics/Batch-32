package com.example.eshopay_be.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopay_be.dto.ApiResponse;
import com.example.eshopay_be.dto.SupplierDTO;
import com.example.eshopay_be.service.BaseService;
import com.example.eshopay_be.service.SupplierService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// import java.util.List;

import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
@Slf4j
public class ApiSupplierController extends ApiBaseController<SupplierDTO, Long> {

    private final SupplierService supplierService;

    @Override
    protected BaseService<SupplierDTO, Long> getService() {
        return supplierService;
    }

    @Override
    public ResponseEntity<ApiResponse<SupplierDTO>> create(@Valid SupplierDTO entity) {

        return super.create(entity);
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> delete(Long id) {
        return super.delete(id);
    }

    // @Override
    // public ResponseEntity<List<SupplierDTO>> getAll() {
    // return super.getAll();
    // }

    @Override
    public ResponseEntity<ApiResponse<SupplierDTO>> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<ApiResponse<SupplierDTO>> update(Long id, @Valid SupplierDTO entity) {
        return super.update(id, entity);
    }
}
