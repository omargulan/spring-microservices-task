package com.example.orders.controller;

import com.example.orders.model.Order;
import com.example.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${users.server.url}")
    private String usersServerUrl;
    @Value("${products.server.url}")
    private String productServerUrl;

    private final OrderRepository orderRepository;

    @PostMapping
    public Order create(@RequestBody Order order){
        String urlUser = usersServerUrl + "/" + order.getUserId();
        String urlProduct = productServerUrl + "/" + order.getProductId();
        order.setStatus(order.getStatus());
        orderRepository.save(order);
        return order;
    }

    @GetMapping("/{orderId}")
    public Order findById(@PathVariable long orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

}
