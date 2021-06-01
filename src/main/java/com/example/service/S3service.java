package com.example.service;

import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

public interface S3service {
    public List<String> uploadFile(MultipartFile[] multipartFile);
}
