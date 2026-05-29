package com.example.learning_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AppUserRequestDTO {

    @NotBlank(message = "Username is required")
    private String username;

    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must contain uppercase, digit, special character and be 8+ characters"
    )
    private String password;

    @Pattern(
            regexp = "ADMIN|USER",
            message = "Role must be ADMIN or USER"
    )
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
