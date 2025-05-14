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

    public static class FindAll {
        public static final String FIND_DATA = "success get all data";
    }

    public static class Update {
        public static final String Update_Multipart = "success update multipart";
        public static final String UPDATE_DATA = "success update data";
    }

    public static class Created {
        public static final String CREATED_DATA = "Success created data";
    }

    public static class GetById {
        public static final String GET_BY_ID = "success get Id";
    }

    public static class Delete {
        public static final String DELETE = "success delete data";
    }
}
