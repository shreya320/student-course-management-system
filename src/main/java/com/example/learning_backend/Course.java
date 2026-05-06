package com.example.learning_backend;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String course_name;

    private int credits;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    public Course() {
    }

    public Course(String course_name, int credits) {
        this.course_name = course_name;
        this.credits = credits;
    }

    public Integer getId() {
        return id;
    }

    public String getCourseName() {
        return course_name;
    }

    public void setCourseName(String course_name) {
        this.course_name = course_name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
