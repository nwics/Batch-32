package com.example.eshopay_be.exception;

public class FileSizeExceededException extends RuntimeException {

    public FileSizeExceededException(String message) {
        super(message);
    }
}
