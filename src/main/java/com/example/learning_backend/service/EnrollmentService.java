package com.example.learning_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learning_backend.entity.Enrollment;
import com.example.learning_backend.repository.EnrollmentRepository;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepository enrollmentRepo;

    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepo.findAll();
    }

    public Enrollment addEnrollment(Enrollment enrollment) {
        return enrollmentRepo.save(enrollment);
    }

    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        return enrollmentRepo.findByStudentId(studentId);
    }

    public List<Enrollment> sampleQueryOne(String str, int marks) {
        return enrollmentRepo.findByStudentNameContainingAndMarksGreaterThan(str, marks);
    }

    public List<Enrollment> sampleQueryTwo(int credits, char grade) {
        return enrollmentRepo.findByCourseCreditsGreaterThanAndGrade(credits, grade);
    }

    public List<Enrollment> marksGreaterThan(int marks) {
        return enrollmentRepo.marksGreaterThan(marks);
    }

    public List<Enrollment> marksGreaterGradeEqual(int marks, char grade) {
        return enrollmentRepo.marksGreaterAndGradeEqual(marks, grade);
    }

    public List<Enrollment> nameContaining(String part) {
        return enrollmentRepo.nameContaining(part);
    }

    public Long totalEnrollments() {
        return enrollmentRepo.totalEnrollments();
    }

    public Double averageMarks() {
        return enrollmentRepo.averageMarks();
    }

    public Integer highestMarks() {
        return enrollmentRepo.highestMarks();
    }

}
