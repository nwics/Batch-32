package com.example.eshopay_be.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.eshopay_be.dto.ProductDTO;
import com.example.eshopay_be.service.BaseService;
import com.example.eshopay_be.service.FileStorageService;
import com.example.eshopay_be.service.ProductsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/product")
public class ApiProductController extends ApiBaseMultipartController<ProductDTO, Long> {
    private final ProductsService productsService;
    private final FileStorageService fileStorageService;

    @Override
    public ResponseEntity<ProductDTO> createMultipart(ProductDTO dto, MultipartFile file, String description) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createMultipart'");
    }

    @Override
    public ResponseEntity<ProductDTO> viewImage(String filename) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewImage'");
    }

    @Override
    public ResponseEntity<ProductDTO> updateMultipart(Long id, ProductDTO dto, MultipartFile file, String description) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMultipart'");
    }

    @Override
    protected BaseService<ProductDTO, Long> getService() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getService'");
        return productsService;
    }

}
