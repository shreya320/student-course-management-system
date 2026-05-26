package com.example.learning_backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CourseRequestDTO {

    @NotBlank(message = "Course name is required")
    private String courseName;

    @Min(value = 1, message = "Credits must be between 1 and 5")
    @Max(value = 5, message = "Credits must be between 1 and 5")
    private int credits;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

}
