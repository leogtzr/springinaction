package com.filegateway.rest;

import com.filegateway.config.Upcase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomResource {

    @Autowired
    private Upcase upcase;

    @GetMapping("/random")
    public String random() {
        return upcase.up("Leo");
        //return upcase.
    }

//    @GetMapping("/random")
//    public String random() {
//        return longRandomGateway.getRandom() + "";
//    }

}
