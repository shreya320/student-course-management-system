package com.example.learning_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.learning_backend.entity.AppUser;
import com.example.learning_backend.service.AppUserService;

@RestController
public class AppUserController {

    @Autowired
    private AppUserService userService;

    @Bean
    private BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }

    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser user) {
        user.setPassword(encoder().encode(user.getPassword()));
        return userService.register(user);
    }
}
