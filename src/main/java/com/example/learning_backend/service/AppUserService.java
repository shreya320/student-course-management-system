package com.example.learning_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.learning_backend.dto.AppUserRequestDTO;
import com.example.learning_backend.dto.AppUserResponseDTO;
import com.example.learning_backend.dto.LoginRequestDTO;
import com.example.learning_backend.entity.AppUser;
import com.example.learning_backend.repository.AppUserRepo;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepo appUserRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    public AppUserResponseDTO register(AppUserRequestDTO userRequest) {
        AppUser user = new AppUser();
        user.setUsername(userRequest.getUsername());
        user.setPassword(
                encoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());

        if (appUserRepository.findByUsername(userRequest.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        AppUser savedUser = appUserRepository.save(user);
        AppUserResponseDTO response = new AppUserResponseDTO();

        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setRole(savedUser.getRole());

        return response;
    }

    public String verify(LoginRequestDTO loginRequest) {
        try {
            Authentication authentication
                    = authManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequest.getUsername(),
                                    loginRequest.getPassword()));

            System.out.println(authentication.isAuthenticated());

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(loginRequest.getUsername());
            }

        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
        }

        return "failed";
    }
}
