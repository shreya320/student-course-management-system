package com.example.learning_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.learning_backend.dto.StudentRequestDTO;
import com.example.learning_backend.dto.StudentResponseDTO;
import com.example.learning_backend.dto.StudentSummaryDTO;
import com.example.learning_backend.entity.Student;
import com.example.learning_backend.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepo;

    public Page<StudentResponseDTO> getAllStudents(Pageable pageable) {

        Page<Student> students = studentRepo.findAll(pageable);
        return students.map(student -> {

            StudentResponseDTO dto = new StudentResponseDTO();

            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setEmail(student.getEmail());

            return dto;
        });
    }

    public StudentResponseDTO addStudent(StudentRequestDTO request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        Student savedStudent = studentRepo.save(student);

        StudentResponseDTO response = new StudentResponseDTO();
        response.setId(savedStudent.getId());
        response.setName(savedStudent.getName());
        response.setEmail(savedStudent.getEmail());
        return response;
    }

    public List<StudentSummaryDTO> getNames() {
        List<StudentSummaryDTO> list = new ArrayList<>();

        List<Student> students = studentRepo.findAll();

        for (Student student : students) {
            StudentSummaryDTO currentStudent = new StudentSummaryDTO();
            currentStudent.setId(student.getId());
            currentStudent.setName(student.getName());

            list.add(currentStudent);
        }

        return list;

    }
}
