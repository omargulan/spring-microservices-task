package com.example.users.controller;

import com.example.users.model.User;
import com.example.users.repostirory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @PostMapping
    public User create(@RequestBody User user){
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        userRepository.save(user);
        return user;
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<User> findAll(){
        return userRepository.findAll();
    }
}
