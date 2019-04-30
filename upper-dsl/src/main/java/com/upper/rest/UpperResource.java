package com.upper.rest;

import com.upper.config.Upcase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpperResource {

    @Autowired
    private Upcase upcase;

    @GetMapping("/up")
    public String random() {
        return upcase.up("Leo");
    }

}
