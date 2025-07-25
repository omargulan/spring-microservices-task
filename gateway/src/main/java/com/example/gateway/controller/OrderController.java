package com.example.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final RestTemplate restTemplate;

    public OrderController(RestTemplateBuilder builder, @Value("http://localhost:8083") String url) {
        this.restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url))
                .build();
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return restTemplate.getForEntity("/orders", Object.class);
    }

    @GetMapping
    public ResponseEntity<Object> findById(@PathVariable long orderId) {
        return restTemplate.getForEntity("/orders/{orderId}", Object.class, orderId);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Object requestObj) {
        return restTemplate.postForEntity("/orders", requestObj, Object.class);
    }
}
