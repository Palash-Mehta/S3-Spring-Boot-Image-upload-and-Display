package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class ClientConfigs {
    @Value("${cloud.aws.access_key_id}")
    private String accessKeyId;
    @Value("${cloud.aws.secret_access_key}")
    private String secretAccessKey;
    @Value("${cloud.aws.s3.region}")
    private String region;

    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(accessKeyId, secretAccessKey);
    }

    @Bean
    public AmazonS3 getAmazonS3Client() {
        // Get AmazonS3 client and return the s3Client object.
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(this.basicAWSCredentials()))
                .build();
    }
}
