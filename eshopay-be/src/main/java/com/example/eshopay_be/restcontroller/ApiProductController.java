package com.example.eshopay_be.restcontroller;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.eshopay_be.dto.ApiResponse;
import com.example.eshopay_be.dto.ProductDTO;
import com.example.eshopay_be.exception.FileSizeExceededException;
import com.example.eshopay_be.service.BaseService;
import com.example.eshopay_be.service.FileStorageService;
import com.example.eshopay_be.service.ProductsService;
import com.example.eshopay_be.util.ErrorMessage;
import com.example.eshopay_be.util.SuccessMessage;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/product")
public class ApiProductController extends ApiBaseMultipartController<ProductDTO, Long> {
    private final ProductsService productsService;
    private final FileStorageService fileStorageService;

    @Override
    public ResponseEntity<ApiResponse<ProductDTO>> createMultipart(ProductDTO dto, MultipartFile file,
            String description) {

        if (file.isEmpty()) {
            ApiResponse<ProductDTO> apiResponse = new ApiResponse<>(ErrorMessage.Photo.UPLOAD_IMAGE, null,
                    LocalDateTime.now(),
                    ErrorMessage.Http.BAD_REQUEST);
            // return new ApiResponse<>("please upload photo", null,
            // LocalDateTime.now(),HttpStatus.BAD_REQUEST.v);
            return ResponseEntity.badRequest().body(apiResponse);
        }
        if (file != null && file.getSize() > 1024L * 1024 * 1024) {
            throw new FileSizeExceededException("File size must less than 1 gb");
        }
        try {
            String filename = fileStorageService.storeFileWithRandomName(file);
            dto.setPictures(filename);
            ProductDTO productDTO = productsService.save(dto);
            ApiResponse<ProductDTO> apiResponse = new ApiResponse<>(
                    SuccessMessage.UploadImage.UPLOAD_IMAGE, productDTO, LocalDateTime.now(), SuccessMessage.Http.OK);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {

            ApiResponse<ProductDTO> apiResponse = new ApiResponse<>(
                    "Error" + e.getMessage(), null, LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            // .body(Collections.singletonMap("error", e.getMessage())
            // );
        }
    }

    @Override
    public ResponseEntity<Resource> viewImage(String filename) {

        try {
            Resource resource = fileStorageService.loadFile(filename);
            String contentType = determineContentType(filename);
            // ApiResponse<Resource> apiResponse = new ApiResponse<>(
            // SuccessMessage.View.View_Image, resource, LocalDateTime.now(),
            // HttpStatus.OK.value());

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<ApiResponse<ProductDTO>> updateMultipart(Long id, ProductDTO dto, MultipartFile file,
            String description) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'updateMultipart'");
        // throw new UnsupportedOperationException("Unplimment method
        // 'UpdateMultipart");
        try {
            ProductDTO existData = productsService.findById(id);
            if (existData == null) {
                return ResponseEntity.notFound().build();
            }
            if (file != null && file.getSize() > 1024L * 1024 * 1024) {
                throw new FileSizeExceededException("file size must less than 1 gb");
            }
            if (file != null && !file.isEmpty()) {
                String fileName = fileStorageService.storeFileWithRandomName(file);
                dto.setPictures(fileName);
                // if (existData.getPictures()!= null) {
                // fileStorageService.deleteFile(existData.getPictures());
                // }

            } else {
                dto.setPictures(existData.getPictures());
            }

            dto.setProductId(id);
            ProductDTO updateData = productsService.update(id, dto);
            ApiResponse<ProductDTO> apiResponse = new ApiResponse<>(
                    SuccessMessage.Update.Update_Multipart, updateData, LocalDateTime.now(), SuccessMessage.Http.OK);
            return ResponseEntity.ok(apiResponse);

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Override
    protected BaseService<ProductDTO, Long> getService() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getService'");
        return productsService;
    }

}
