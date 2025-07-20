package com.example.products.controller;

import com.example.products.model.Product;
import com.example.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    @PostMapping
    public Product create(@RequestBody Product product){
        product.setName(product.getName());
        product.setPrice(product.getPrice());
        productRepository.save(product);
        return product;
    }

    @GetMapping("/{productId}")
    public Product findById(@PathVariable long productId){
        return productRepository.findById(productId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
