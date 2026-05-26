package com.example.learning_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.learning_backend.entity.Enrollment;
import com.example.learning_backend.service.EnrollmentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class EnrollmentController {

    @Autowired
    EnrollmentService service;

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/enrollments")
    public ResponseEntity<List<Enrollment>> getEnrollment() {
        if (service.getAllEnrollment() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.getAllEnrollment());
    }

    @PostMapping("/enrollments")
    public ResponseEntity<?> addEnrollment(@RequestBody Enrollment enroll) {
        return ResponseEntity.ok(service.addEnrollment(enroll));
    }

    @GetMapping("/enrollments/students/{studentId}")
    public ResponseEntity<List<Enrollment>> findByStudentId(@PathVariable int studentId) {
        return ResponseEntity.ok(service.getEnrollmentsByStudent(studentId));
    }

    @GetMapping("/enrollments/search/{str}/{marks}")
    public ResponseEntity<List<Enrollment>> exampleQueryOne(@PathVariable String str, @PathVariable int marks) {
        return ResponseEntity.ok(service.sampleQueryOne(str, marks));
    }

    @GetMapping("/enrollments/filter/{credits}/{grade}")
    public ResponseEntity<List<Enrollment>> exampleQueryTwo(@PathVariable int credits, @PathVariable char grade) {
        return ResponseEntity.ok(service.sampleQueryTwo(credits, grade));
    }

    @GetMapping("/enrollments/marksGreater")
    public ResponseEntity<List<Enrollment>> greaterMarks(@RequestParam int marks) {
        return ResponseEntity.ok(service.marksGreaterThan(marks));
    }

    @GetMapping("/enrollments/marksAndGrade")
    public ResponseEntity<List<Enrollment>> marksAndGrade(@RequestParam int marks, @RequestParam char grade) {
        return ResponseEntity.ok(service.marksGreaterGradeEqual(marks, grade));
    }

    @GetMapping("/enrollments/nameContaining")
    public ResponseEntity<List<Enrollment>> nameContaining(@RequestParam String part) {
        return ResponseEntity.ok(service.nameContaining(part));
    }

    @GetMapping("/enrollments/total")
    public ResponseEntity<Long> totalEnrollments() {
        return ResponseEntity.ok(service.totalEnrollments());
    }

    @GetMapping("/enrollments/avgMarks")
    public ResponseEntity<Double> averageMarks() {
        return ResponseEntity.ok(service.averageMarks());
    }

    @GetMapping("/enrollments/highestMarks")
    public ResponseEntity<Integer> highestMarks() {
        return ResponseEntity.ok(service.highestMarks());
    }

}
