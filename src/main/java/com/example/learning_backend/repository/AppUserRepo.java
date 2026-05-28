package com.example.learning_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.learning_backend.entity.AppUser;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Integer> {

    AppUser findByUsername(String username);
}
