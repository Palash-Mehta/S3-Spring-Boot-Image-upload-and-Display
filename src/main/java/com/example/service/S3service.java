package com.example.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3service {
    void uploadFile(MultipartFile[] multipartFile);
}
