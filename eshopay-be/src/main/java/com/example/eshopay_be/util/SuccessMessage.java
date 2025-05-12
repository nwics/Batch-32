package com.example.eshopay_be.util;

public class SuccessMessage {
    public static class Http {

        public static final int OK = 200;
        public static final int CREATED = 201;
        public static final int ACCEPTED = 202;
        public static final int NO_CONTENT = 204;

        public static String getMessage(int statusCode) {
            switch (statusCode) {
                case OK:
                    return "OK";
                case CREATED:
                    return "Created";
                case ACCEPTED:
                    return "Accepted";
                case NO_CONTENT:
                    return "No Content";

                default:
                    return "Unknown Error";
            }
        }

    }

    public static class UploadImage {
        public static final String UPLOAD_IMAGE = "success upload image";
    }

    public static class Update {
        public static final String Update_Multipart = "success update multipart";
    }
}
