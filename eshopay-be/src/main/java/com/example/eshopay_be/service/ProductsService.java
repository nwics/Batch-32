package com.example.eshopay_be.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.eshopay_be.dto.ProductDTO;
import com.example.eshopay_be.dto.ProductPhotoDTO;
import com.example.eshopay_be.model.ProductImages;

public interface ProductsService extends BaseService<ProductDTO, Long> {

    ProductImages UploadProductImage(MultipartFile file, Long productId);

    ProductImages findProductImagesById(Long productId);
}
