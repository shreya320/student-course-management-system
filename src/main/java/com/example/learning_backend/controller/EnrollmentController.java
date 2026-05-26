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

import com.example.learning_backend.dto.EnrollmentRequestDTO;
import com.example.learning_backend.dto.EnrollmentResponseDTO;
import com.example.learning_backend.service.EnrollmentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
public class EnrollmentController {

    @Autowired
    EnrollmentService service;

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/enrollments")
    public ResponseEntity<List<EnrollmentResponseDTO>> getEnrollment() {

        return ResponseEntity.ok(service.getAllEnrollment());
    }

    @PostMapping("/enrollments")
    public ResponseEntity<EnrollmentResponseDTO> addEnrollment(
            @Valid @RequestBody EnrollmentRequestDTO enrollRequest) {

        return ResponseEntity.ok(service.addEnrollment(enrollRequest));
    }

    @GetMapping("/enrollments/students/{studentId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> findByStudentId(
            @PathVariable int studentId) {

        return ResponseEntity.ok(service.getEnrollmentsByStudent(studentId));
    }

    @GetMapping("/enrollments/search/{str}/{marks}")
    public ResponseEntity<List<EnrollmentResponseDTO>> exampleQueryOne(
            @PathVariable String str,
            @PathVariable int marks) {

        return ResponseEntity.ok(service.sampleQueryOne(str, marks));
    }

    @GetMapping("/enrollments/filter/{credits}/{grade}")
    public ResponseEntity<List<EnrollmentResponseDTO>> exampleQueryTwo(
            @PathVariable int credits,
            @PathVariable char grade) {

        return ResponseEntity.ok(service.sampleQueryTwo(credits, grade));
    }

    @GetMapping("/enrollments/marksGreater")
    public ResponseEntity<List<EnrollmentResponseDTO>> greaterMarks(
            @RequestParam int marks) {

        return ResponseEntity.ok(service.marksGreaterThan(marks));
    }

    @GetMapping("/enrollments/marksAndGrade")
    public ResponseEntity<List<EnrollmentResponseDTO>> marksAndGrade(
            @RequestParam int marks,
            @RequestParam char grade) {

        return ResponseEntity.ok(service.marksGreaterGradeEqual(marks, grade));
    }

    @GetMapping("/enrollments/nameContaining")
    public ResponseEntity<List<EnrollmentResponseDTO>> nameContaining(
            @RequestParam String part) {

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
