package com.example.eshopay_be.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.eshopay_be.dao.ProductsRepository;
import com.example.eshopay_be.dto.ProductDTO;
import com.example.eshopay_be.dto.SupplierDTO;
import com.example.eshopay_be.model.Category;
import com.example.eshopay_be.model.Products;
import com.example.eshopay_be.model.Suppliers;
import com.example.eshopay_be.service.ProductsService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    public static ProductDTO mapToDto(Products products) {

        return new ProductDTO(products.getProductId(), products.getProductName(), products.getQuantityPerUnit(),
                products.getUnitPrice(), products.getUnitsInStock(), products.getUnitsOnOrder(),
                products.getReorderLevel(), products.getDiscontinued(),
                SupplierServiceImpl.mapToDto(products.getSuppliers()),
                CategoryServiceImpl.mapToDTO(products.getCategory()));
    }

    public static Products mapToModel(ProductDTO productDTO) {
        return new Products(productDTO.getProductId(), productDTO.getProductName(), productDTO.getQuantityPerUnit(),
                productDTO.getUnitPrice(), productDTO.getUnitsInStock(), productDTO.getUnitsOnOrder(),
                productDTO.getReorderLevel(),
                productDTO.getDiscontinued(),
                SupplierServiceImpl.mapToModel(productDTO.getSupplierDTO()),
                CategoryServiceImpl.mapToModel(productDTO.getCategoryDTO()));
    }

    @Override
    public List<ProductDTO> findAll() {

        return this.productsRepository.findAll().stream().map(ProductServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'findById'");
        return productsRepository.findById(id).map(ProductServiceImpl::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("product id not found" + id));
    }

    @Override
    public ProductDTO save(ProductDTO entity) {

        Products products = mapToModel(entity);
        return mapToDto(productsRepository.save(products));
    }

    @Override
    public ProductDTO update(Long id, ProductDTO entity) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'update'");
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("product id not found" + id));
        products.setProductId(entity.getProductId());
        products.setProductName(entity.getProductName());
        products.setQuantityPerUnit(entity.getQuantityPerUnit());
        products.setUnitPrice(entity.getUnitPrice());
        products.setUnitsInStock(entity.getUnitsInStock());
        products.setUnitsOnOrder(entity.getUnitsOnOrder());
        products.setReorderLevel(entity.getReorderLevel());
        products.setDiscontinued(entity.getDiscontinued());
        products.setSuppliers(
                new Suppliers(entity.getSupplierDTO().getSupplierId(), entity.getSupplierDTO().getCompanyName()));
        products.setCategory(new Category(entity.getProductId(), entity.getCategoryDTO().getCategoryName(),
                entity.getCategoryDTO().getDescription(), null));
        return mapToDto(productsRepository.save(products));
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'delete'");
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id not found" + id));
        productsRepository.deleteById(products.getProductId());
    }

}
