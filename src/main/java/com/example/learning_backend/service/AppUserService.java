package com.example.learning_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learning_backend.entity.AppUser;
import com.example.learning_backend.repository.AppUserRepo;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepo appUserRepository;

    public AppUser register(AppUser user) {
        return appUserRepository.save(user);
    }

}
