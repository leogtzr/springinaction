package com.books.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DummyController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/call")
    public ResponseEntity<String> call() {
        return ResponseEntity.ok().body(
                restTemplate.getForObject("http://localhost:8080/libros", String.class)
        );
    }

}
