package com.example.learning_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.learning_backend.dto.StudentRequestDTO;
import com.example.learning_backend.dto.StudentResponseDTO;
import com.example.learning_backend.dto.StudentSummaryDTO;
import com.example.learning_backend.service.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping("/students")
    public ResponseEntity<Page<StudentResponseDTO>> getStudents(Pageable pageable) {
        return ResponseEntity.ok(service.getAllStudents(pageable));
    }

    @PostMapping("/students")
    public ResponseEntity<StudentResponseDTO> addStudent(@Valid @RequestBody StudentRequestDTO studentRequest) {
        return ResponseEntity.ok(service.addStudent(studentRequest));
    }

    @GetMapping("/studentsName")
    public ResponseEntity<List<StudentSummaryDTO>> getNames() {
        return ResponseEntity.ok(service.getNames());
    }

}
