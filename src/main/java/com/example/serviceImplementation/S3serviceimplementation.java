package com.example.serviceImplementation;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.service.S3service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class S3serviceimplementation implements S3service {

    private static final Logger LOGGER = LoggerFactory.getLogger(S3serviceimplementation.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;


    @Override
    @Async
    public List<String> uploadFile(final MultipartFile[] multipartFile){
        LOGGER.info("File upload in progress.");
        List<String> imageURL = new ArrayList<>();
        for(MultipartFile uploadFile:multipartFile) {
            try {
                final File file = convertMultiPartFileToFile(uploadFile);
                final String uniqueFileName = LocalDateTime.now() + "_" + file.getName();
                final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file);
                amazonS3.putObject(putObjectRequest);
                file.delete();
                imageURL.add(amazonS3.getUrl(bucketName,uniqueFileName).toString());
            } catch (final AmazonServiceException ex) {
                LOGGER.info("<<--Error-->>");
                LOGGER.error("There was an error in uploading {} file", ex.getMessage());
            }
        };
        System.out.println(imageURL);
        return imageURL;
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile){
        final File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
        }
        return file;
    }
}
