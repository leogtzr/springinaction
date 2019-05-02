package com.upper.rest;

import com.upper.config.LowercaseGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LowercaseResource {

    @Autowired
    private LowercaseGateway lowercaseGateway;

    @GetMapping("/lower/{str}")
    public String lower(@PathVariable("str") final String str) {
        return lowercaseGateway.lower(str);
    }

}
