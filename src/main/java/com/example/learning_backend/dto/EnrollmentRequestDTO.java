package com.example.learning_backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class EnrollmentRequestDTO {

    @NotNull(message = "Student ID is required")
    private Integer studentId;

    @NotNull(message = "Student ID is required")
    private Integer courseId;

    private char grade;

    @Min(value = 0, message = "Please enter valid marks")
    @Max(value = 100, message = "Please enter valid marks")
    private int marks;

    @Min(value = 1, message = "Please enter semseter between 1 and 8")
    @Max(value = 8, message = "Please enter semseter between 1 and 8")
    private int semester;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
