package com.example.learning_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.learning_backend.dto.AppUserRequestDTO;
import com.example.learning_backend.dto.AppUserResponseDTO;
import com.example.learning_backend.dto.LoginRequestDTO;
import com.example.learning_backend.service.AppUserService;

import jakarta.validation.Valid;

@RestController
public class AppUserController {

    @Autowired
    private AppUserService userService;

    @PostMapping("/register")
    public AppUserResponseDTO register(
            @Valid @RequestBody AppUserRequestDTO userRequest) {

        return userService.register(userRequest);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        return userService.verify(loginRequest);
    }
}
