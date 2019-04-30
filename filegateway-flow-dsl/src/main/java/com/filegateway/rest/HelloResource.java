package com.filegateway.rest;

import com.filegateway.config.FileWriterGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    @Autowired
    private FileWriterGateway fileWriterGateway;

    @GetMapping("/hello")
    public String hello() {
        fileWriterGateway.writeToFile("kaka.txt", "holis");
        return "holis";
    }

}
