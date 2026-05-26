package com.example.learning_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learning_backend.dto.CourseRequestDTO;
import com.example.learning_backend.dto.CourseResponseDTO;
import com.example.learning_backend.entity.Course;
import com.example.learning_backend.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepo;

    public List<CourseResponseDTO> getAllCourse() {
        List<Course> courses = courseRepo.findAll();
        List<CourseResponseDTO> response = new ArrayList<>();

        for (Course course : courses) {
            CourseResponseDTO dto = new CourseResponseDTO();
            dto.setId(course.getId());
            dto.setCourseName(course.getCourseName());
            dto.setCredits(course.getCredits());

            response.add(dto);
        }

        return response;
    }

    public CourseResponseDTO addCourse(CourseRequestDTO request) {
        Course course = new Course();

        course.setCourseName(request.getCourseName());
        course.setCredits(request.getCredits());

        Course savedCourse = courseRepo.save(course);

        CourseResponseDTO response = new CourseResponseDTO();

        response.setId(savedCourse.getId());
        response.setCourseName(savedCourse.getCourseName());
        response.setCredits(savedCourse.getCredits());

        return response;
    }
}
