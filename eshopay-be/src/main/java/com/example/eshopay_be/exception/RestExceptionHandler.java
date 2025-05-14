package com.example.eshopay_be.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.eshopay_be.dto.ApiResponse;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleProductsNotFound(ProductNotFoundException e) {
        ApiResponse<Object> errorResponse = createErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileSizeExceededException.class)
    public ResponseEntity<ApiResponse<Object>> handleFileSizeExceeded(FileSizeExceededException e) {
        ApiResponse<Object> errorResponse = createErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ApiResponse<Object> createErrorResponse(String message) {
        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setMessage(message);
        errorResponse.setStatusCode(404);
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setData(null);
        return errorResponse;
    }
}
