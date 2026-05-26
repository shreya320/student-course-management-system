package com.example.learning_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learning_backend.entity.Course;
import com.example.learning_backend.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepo;

    public List<Course> getAllCourse() {
        return courseRepo.findAll();
    }

    public Course addCourse(Course course) {
        return courseRepo.save(course);
    }
}
