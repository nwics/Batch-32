package com.example.eshopay_be.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public abstract class ApiBaseMultipartController<T, ID> extends ApiBaseController<T, ID> {

    @PostMapping(consumes = { "multipart/form-data" }, value = "/upload")
    public abstract ResponseEntity<T> createMultipart(
            @RequestPart("data") T dto,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "description", required = false) String description);

    @GetMapping("/view/{filename:.+}")
    public abstract ResponseEntity<T> viewImage(@PathVariable String filename);

    public String determineContentType(String filename) {
        if (filename.toLowerCase().endsWith(".png")) {
            return "image/png";
        } else if (filename.toLowerCase().endsWith(".jpg") || (filename.toLowerCase().endsWith(".jpeg"))) {
            return "image/jpeg";
        } else if (filename.toLowerCase().endsWith(".gif")) {
            return "image/gif";
        }
        return "application/octet-stream";
    }

    @PutMapping(value = "/{id}", consumes = { "multipart/form-data" })
    public abstract ResponseEntity<T> updateMultipart(
            @PathVariable ID id,
            @RequestPart("data") T dto,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "description", required = false) String description);

}
