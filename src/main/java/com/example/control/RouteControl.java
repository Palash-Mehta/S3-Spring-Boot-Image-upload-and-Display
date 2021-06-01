package com.example.control;

import com.example.service.S3service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.net.URL;
import java.util.List;

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
        public ModelAndView uploadFile(@RequestPart(value= "file") final MultipartFile[] multipartFile){
            ModelAndView returnPage = new ModelAndView();
            List<String> imageURL = s3service.uploadFile(multipartFile);
            LOGGER.info("Files have uploaded successfully");
            returnPage.setViewName("display");
            returnPage.addObject("imageURL", imageURL);
            return returnPage;
    }
}
