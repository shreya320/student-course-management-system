package com.example.learning_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.learning_backend.entity.Course;
import com.example.learning_backend.service.CourseService;

@RestController
public class CourseController {

    @Autowired
    CourseService service;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses() {
        if (service.getAllCourse() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.getAllCourse());
    }

    @PostMapping("/courses")
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        return ResponseEntity.ok(service.addCourse(course));
    }
}
