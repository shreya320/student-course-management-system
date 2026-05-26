package com.example.learning_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learning_backend.dto.EnrollmentRequestDTO;
import com.example.learning_backend.dto.EnrollmentResponseDTO;
import com.example.learning_backend.entity.Course;
import com.example.learning_backend.entity.Enrollment;
import com.example.learning_backend.entity.Student;
import com.example.learning_backend.repository.CourseRepository;
import com.example.learning_backend.repository.EnrollmentRepository;
import com.example.learning_backend.repository.StudentRepository;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepository enrollmentRepo;

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    CourseRepository courseRepo;

    private EnrollmentResponseDTO convertToDTO(Enrollment enrollment) {

        EnrollmentResponseDTO dto = new EnrollmentResponseDTO();

        dto.setId(enrollment.getId());

        dto.setStudentId(enrollment.getStudent().getId());
        dto.setStudentName(enrollment.getStudent().getName());

        dto.setCourseId(enrollment.getCourse().getId());
        dto.setCourseName(enrollment.getCourse().getCourseName());

        dto.setGrade(enrollment.getGrade());
        dto.setMarks(enrollment.getMarks());
        dto.setSemester(enrollment.getSemester());

        return dto;
    }

    public List<EnrollmentResponseDTO> getAllEnrollment() {

        List<Enrollment> enrollments = enrollmentRepo.findAll();

        List<EnrollmentResponseDTO> response = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            response.add(convertToDTO(enrollment));
        }

        return response;
    }

    public EnrollmentResponseDTO addEnrollment(EnrollmentRequestDTO request) {

        Student student = studentRepo.findById(request.getStudentId())
                .orElseThrow();

        Course course = courseRepo.findById(request.getCourseId())
                .orElseThrow();

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setGrade(request.getGrade());
        enrollment.setMarks(request.getMarks());
        enrollment.setSemester(request.getSemester());

        Enrollment savedEnrollment = enrollmentRepo.save(enrollment);

        return convertToDTO(savedEnrollment);
    }

    public List<EnrollmentResponseDTO> getEnrollmentsByStudent(int studentId) {

        List<Enrollment> enrollments = enrollmentRepo.findByStudentId(studentId);

        List<EnrollmentResponseDTO> response = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            response.add(convertToDTO(enrollment));
        }

        return response;
    }

    public List<EnrollmentResponseDTO> sampleQueryOne(String str, int marks) {

        List<Enrollment> enrollments
                = enrollmentRepo.findByStudentNameContainingAndMarksGreaterThan(str, marks);

        List<EnrollmentResponseDTO> response = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            response.add(convertToDTO(enrollment));
        }

        return response;
    }

    public List<EnrollmentResponseDTO> sampleQueryTwo(int credits, char grade) {

        List<Enrollment> enrollments
                = enrollmentRepo.findByCourseCreditsGreaterThanAndGrade(credits, grade);

        List<EnrollmentResponseDTO> response = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            response.add(convertToDTO(enrollment));
        }

        return response;
    }

    public List<EnrollmentResponseDTO> marksGreaterThan(int marks) {

        List<Enrollment> enrollments = enrollmentRepo.marksGreaterThan(marks);

        List<EnrollmentResponseDTO> response = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            response.add(convertToDTO(enrollment));
        }

        return response;
    }

    public List<EnrollmentResponseDTO> marksGreaterGradeEqual(int marks, char grade) {

        List<Enrollment> enrollments
                = enrollmentRepo.marksGreaterAndGradeEqual(marks, grade);

        List<EnrollmentResponseDTO> response = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            response.add(convertToDTO(enrollment));
        }

        return response;
    }

    public List<EnrollmentResponseDTO> nameContaining(String part) {

        List<Enrollment> enrollments = enrollmentRepo.nameContaining(part);

        List<EnrollmentResponseDTO> response = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            response.add(convertToDTO(enrollment));
        }

        return response;
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
