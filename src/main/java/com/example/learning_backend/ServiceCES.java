package com.example.learning_backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCES {

    @Autowired
    StudentRepository studentRepo;
    @Autowired
    CourseRepository courseRepo;
    @Autowired
    EnrollmentRepository enrollmentRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public List<Course> getAllCourse() {
        return courseRepo.findAll();
    }

    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepo.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public Course addCourse(Course course) {
        return courseRepo.save(course);
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
