package com.example.eshopay_be.exception;

public class InsunfficientStockProduct extends RuntimeException {

    public InsunfficientStockProduct(String message) {
        super(message);
    }
}
