package com.example.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final RestTemplate restTemplate;

    public ProductController(RestTemplateBuilder builder, @Value("http://localhost:8082") String url) {
        this.restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url))
                .build();
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return restTemplate.getForEntity("/products", Object.class);
    }

    @GetMapping
    public ResponseEntity<Object> findById(@PathVariable long productId) {
        return restTemplate.getForEntity("/users/{productId}", Object.class, productId);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Object requestObj) {
        return restTemplate.postForEntity("/products", requestObj, Object.class);
    }
}
