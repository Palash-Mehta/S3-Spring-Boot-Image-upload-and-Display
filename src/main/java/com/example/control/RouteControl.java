package com.example.control;

import com.example.service.S3service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RouteControl {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteControl.class);

    @Autowired
    private S3service s3service;

    @RequestMapping(value = "/")
        public String index(){
            return "index";
    }

    @PostMapping(value = "/")
    @ResponseBody
        public String uploadFile(@RequestPart(value= "file") final MultipartFile[] multipartFile){
            s3service.uploadFile(multipartFile);
            LOGGER.info("Files have uploaded successfully");
            return "index";
    }
}
