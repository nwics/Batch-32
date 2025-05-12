package com.example.eshopay_be.util;

public class ErrorMessage {

    public static class Http {
        public static final int BAD_REQUEST = 400;
        public static final int UNAUTHORIZED = 401;
        public static final int FORBIDDEN = 403;
        public static final int NOT_FOUND = 404;

        public static String getMessage(int statusCode) {
            switch (statusCode) {
                case BAD_REQUEST:
                    return "Bad Request";
                case UNAUTHORIZED:
                    return "Unauthorized";
                case FORBIDDEN:
                    return "Forbidden";
                case NOT_FOUND:
                    return "Not Found";
                default:
                    return "Unknown Error";
            }
        }
    }

    public static class Photo {
        public static final String UPLOAD_IMAGE = "need upload photo";
    }

    public static class Product {
        public static final String PRODUCT_NOT_FOUND = "product not found";
    }

    public static class Category {
        public static final String CATEGORY_NOT_FOUND = "category not found";

    }

    public static class Supplier {
        public static final String SUPPLIER_NOT_FOUND = "supplier not found";
    }

    public static class shipper {
        public static final String SHIPPER_NOT_FOUND = "shipper not found";

    }
}
