package com.example.learning_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    public AppUserResponseDTO register(AppUserRequestDTO userRequest) {
        AppUser user = new AppUser();
        user.setUsername(userRequest.getUsername());
        user.setPassword(
                encoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());

        AppUser savedUser = appUserRepository.save(user);
        AppUserResponseDTO response = new AppUserResponseDTO();

        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setRole(savedUser.getRole());

        return response;
    }

    public AppUserResponseDTO verify(LoginRequestDTO loginRequest) {
        Authentication authentication
                = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            AppUser user = appUserRepository.findByUsername(loginRequest.getUsername());
            AppUserResponseDTO response = new AppUserResponseDTO();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setRole(user.getRole());
            return response;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
