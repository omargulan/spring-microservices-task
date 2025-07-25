package com.example.gateway.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@RestController
@RequestMapping("/users")
public class UserController {
    private final RestTemplate restTemplate;

    public UserController(RestTemplateBuilder builder, @Value("http://localhost:8081") String url) {
        this.restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url))
                .build();
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return restTemplate.getForEntity("/users", Object.class);
    }

    @GetMapping
    public ResponseEntity<Object> findById(@PathVariable long userId) {
        return restTemplate.getForEntity("/users/{userId}", Object.class, userId);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Object requestObj) {
        return restTemplate.postForEntity("/users", requestObj, Object.class);
    }
}
