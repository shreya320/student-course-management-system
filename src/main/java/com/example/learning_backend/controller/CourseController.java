package com.example.learning_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.learning_backend.dto.CourseRequestDTO;
import com.example.learning_backend.dto.CourseResponseDTO;
import com.example.learning_backend.service.CourseService;

import jakarta.validation.Valid;

@RestController
public class CourseController {

    @Autowired
    CourseService service;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponseDTO>> getCourses() {
        if (service.getAllCourse() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.getAllCourse());
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseResponseDTO> addCourse(@Valid @RequestBody CourseRequestDTO course) {
        return ResponseEntity.ok(service.addCourse(course));
    }
}
