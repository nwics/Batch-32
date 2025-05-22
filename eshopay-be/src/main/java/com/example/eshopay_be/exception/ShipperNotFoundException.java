package com.example.eshopay_be.exception;

public class ShipperNotFoundException extends RuntimeException {

    public ShipperNotFoundException(String message) {
        super(message);
    }
}
