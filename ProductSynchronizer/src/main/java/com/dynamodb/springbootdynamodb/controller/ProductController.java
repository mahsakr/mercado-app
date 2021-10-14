package com.dynamodb.springbootdynamodb.controller;

import com.dynamodb.springbootdynamodb.service.FetchFiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private FetchFiles fetchFiles;

    @Value("${cloud.aws.sqs.endpoint}")
    private String sqsEndpoint;

    @PostMapping("/import")
    @ResponseStatus(HttpStatus.OK)
    public void readFiles() {
        fetchFiles.readFiles();
    }


}
