package com.example.eshopay_be.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.InsufficientResourcesException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.eshopay_be.dao.CategoryRepository;
import com.example.eshopay_be.dao.ProductPhotoRepository;
import com.example.eshopay_be.dao.ProductsRepository;
import com.example.eshopay_be.dao.specs.ProductItemSpec;
import com.example.eshopay_be.dto.ApiResponsePagination;
import com.example.eshopay_be.dto.Filter;
import com.example.eshopay_be.dto.Pagination;
import com.example.eshopay_be.dto.ProductDTO;
import com.example.eshopay_be.dto.ProductPhotoDTO;
import com.example.eshopay_be.dto.SupplierDTO;
import com.example.eshopay_be.exception.InsunfficientStockProduct;
import com.example.eshopay_be.exception.ProductNotFoundException;
import com.example.eshopay_be.exception.ResourceNotFoundException;
import com.example.eshopay_be.model.Category;
import com.example.eshopay_be.model.ProductImages;
import com.example.eshopay_be.model.Products;
import com.example.eshopay_be.model.Suppliers;
import com.example.eshopay_be.service.FileStorageService;
import com.example.eshopay_be.service.ProductsService;
import com.example.eshopay_be.util.ErrorMessage;
import com.example.eshopay_be.util.SuccessMessage;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
// import lombok.Value;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductsService {

        private final ProductsRepository productsRepository;

        private final ProductPhotoRepository productPhotoRepository;

        private final FileStorageService fileStorageService;
        @Value("${app.endpoint-file-view}")
        private String endpointFileView;

        public static ProductDTO mapToDto(Products products) {

                return new ProductDTO(products.getProductId(), products.getProductName(), products.getQuantityPerUnit(),
                                products.getUnitPrice(), products.getUnitsInStock(), products.getUnitsOnOrder(),
                                products.getReorderLevel(), products.getDiscontinued(), products.getPictures(),
                                SupplierServiceImpl.mapToDto(products.getSuppliers()),
                                CategoryServiceImpl.mapToDTO(products.getCategory()));
        }

        public static Products mapToModel(ProductDTO productDTO) {
                return new Products(productDTO.getProductId(), productDTO.getProductName(),
                                productDTO.getQuantityPerUnit(),
                                productDTO.getUnitPrice(), productDTO.getUnitsInStock(), productDTO.getUnitsOnOrder(),
                                productDTO.getReorderLevel(),
                                productDTO.getDiscontinued(), productDTO.getPictures(),
                                SupplierServiceImpl.mapToModel(productDTO.getSupplierDTO()),
                                CategoryServiceImpl.mapToModel(productDTO.getCategoryDTO()));
        }

        @Override
        public ApiResponsePagination<ProductDTO> findAll(Integer size, Integer current, String keyword, String category,
                        String sortingDirection) {

                /*
                 * Search
                 * https://www.baeldung.com/rest-api-search-language-spring-data-specifications
                 */
                Specification<Products> specs = ProductItemSpec.searchSpecification(keyword, category);

                // Sorting
                Sort sort = sortingDirection.equalsIgnoreCase("desc") ? Sort.by("productId").descending()
                                : Sort.by("productId").ascending();

                // get all data
                Pageable pageable = PageRequest.of(current - 1, size, sort);
                Page<Products> pageResult = productsRepository.findAll(specs, pageable);
                List<ProductDTO> productDTOs = pageResult.getContent().stream().map(ProductServiceImpl::mapToDto)
                                .collect(Collectors.toList());

                Pagination pagination = new Pagination();
                pagination.setCurrent(current);
                pagination.setSize(size);
                pagination.setTotal(pageResult.getTotalElements());
                pagination.setTotalPages(pageResult.getTotalPages());
                pagination.setFilter(
                                Filter.builder()
                                                .keyword(keyword)
                                                .category(category)
                                                .build());

                ApiResponsePagination<ProductDTO> response = new ApiResponsePagination<>();
                response.setMessage(SuccessMessage.FindAll.FIND_DATA);
                response.setStatusCode(200);
                response.setData(productDTOs);
                response.setTimestamp(LocalDateTime.now());
                response.setPage(pagination);

                return response;
        }

        @Override
        public ProductDTO findById(Long id) {

                return productsRepository.findById(id).map(ProductServiceImpl::mapToDto)
                                .orElseThrow(() -> new ProductNotFoundException(
                                                ErrorMessage.Product.PRODUCT_NOT_FOUND));
        }

        @Override
        public ProductDTO save(ProductDTO entity) {

                Products products = mapToModel(entity);
                return mapToDto(productsRepository.save(products));
        }

        @Override
        public ProductDTO update(Long id, ProductDTO entity) {

                Products products = productsRepository.findById(id)
                                .orElseThrow(() -> new ProductNotFoundException(
                                                ErrorMessage.Product.PRODUCT_NOT_FOUND));
                products.setProductId(entity.getProductId());
                products.setProductName(entity.getProductName());
                products.setQuantityPerUnit(entity.getQuantityPerUnit());
                products.setUnitPrice(entity.getUnitPrice());
                products.setUnitsInStock(entity.getUnitsInStock());
                products.setUnitsOnOrder(entity.getUnitsOnOrder());
                products.setReorderLevel(entity.getReorderLevel());
                products.setPictures(entity.getPictures());
                products.setDiscontinued(entity.getDiscontinued());
                products.setSuppliers(
                                new Suppliers(entity.getSupplierDTO().getSupplierId(),
                                                entity.getSupplierDTO().getCompanyName()));
                products.setCategory(new Category(entity.getProductId(), entity.getCategoryDTO().getCategoryName(),
                                entity.getCategoryDTO().getDescription(), null));
                return mapToDto(productsRepository.save(products));
        }

        @Override
        public void delete(Long id) {

                Products products = productsRepository.findById(id)
                                .orElseThrow(() -> new ProductNotFoundException(
                                                ErrorMessage.Product.PRODUCT_NOT_FOUND));
                productsRepository.deleteById(products.getProductId());
        }

        @Override
        public ProductImages UploadProductImage(MultipartFile file, Long productId) {

                try {
                        Products products = productsRepository.findById(productId)
                                        .orElseThrow(() -> new ProductNotFoundException(
                                                        ErrorMessage.Product.PRODUCT_NOT_FOUND));

                        String storeFileName = fileStorageService.storeFileWithRandomName(file);

                        ProductImages image = ProductImages.builder()
                                        .fileName(storeFileName)
                                        .fileSize(file.getSize())
                                        .fileType(file.getContentType())
                                        .fileUrl(endpointFileView + storeFileName)
                                        .products(products)
                                        .build();

                        return productPhotoRepository.save(image);

                } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        throw new RuntimeException("error upload image " + e.getMessage(), e);
                }

        }

        @Override
        public List<ProductPhotoDTO> findProductImagesById(Long productId) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method
                // 'findProductImagesById'");
                Products products = productsRepository.findById(productId)
                                .orElseThrow(() -> new ProductNotFoundException(
                                                ErrorMessage.Product.PRODUCT_NOT_FOUND));

                List<ProductImages> images = productPhotoRepository.findByProducts(products);
                return images.stream().map(image -> ProductPhotoDTO.builder()
                                .producttoId(image.getProductToId())
                                .fileName(image.getFileName())
                                .fileSize(image.getFileSize())
                                .fileType(image.getFileType())
                                .fileUri(image.getFileUrl())
                                .productId(productId)
                                .build()).collect(Collectors.toList());

        }

        @Override
        @Transactional
        public void updateProductStock(Long productId, Integer quantity) {

                Products products = productsRepository.findById(productId)
                                .orElseThrow(() -> new ProductNotFoundException(
                                                ErrorMessage.Product.PRODUCT_NOT_FOUND + productId));

                int newStock = products.getUnitsInStock() - quantity;
                if (newStock < 0) {
                        throw new ProductNotFoundException(
                                        ErrorMessage.Product.PRODUCT_STOCK_INSUNFFICIENT + productId);
                }
                products.setUnitsInStock(newStock);
                productsRepository.save(products);
        }

}
