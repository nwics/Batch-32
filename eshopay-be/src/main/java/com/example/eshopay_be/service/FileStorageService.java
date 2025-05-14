package com.example.eshopay_be.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService(@Value("${file.upload-dir}") String uploadDir) throws Exception {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
    }

    public String storeFileWithRandomName(MultipartFile file) throws Exception {

        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileNameExtension = "";
        if (originalFileName.contains(".")) {
            fileNameExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        String randomName = UUID.randomUUID().toString() + fileNameExtension;
        try {
            if (randomName.contains("..")) {
                throw new Exception("nama file tidak valid" + randomName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(randomName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return randomName;
        } catch (IOException e) {
            throw new Exception("gagal menyimpan data " + randomName, e);
        }
    }

    public Resource loadFile(String fileName) throws Exception {
        try {
            Path pathFile = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(pathFile.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new Exception("gagal menemukan file " + fileName);
            }
        } catch (MalformedURLException e) {
            // TODO: handle exception
            throw new Exception("file tidak ditemuakn" + fileName, e);
        }
    }

    public void deleteFile(String filename) {
        try {
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("failed to deleted: " + filename, e);
        }
    }

}
