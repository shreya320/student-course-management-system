package com.example.learning_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.learning_backend.entity.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    List<Enrollment> findByStudentId(Integer studentId);

    List<Enrollment> findByStudentNameContainingAndMarksGreaterThan(String str, int marks);

    List<Enrollment> findByCourseCreditsGreaterThanAndGrade(int credits, char grade);

    @Query("select e from Enrollment e where e.marks > :marks")
    List<Enrollment> marksGreaterThan(@Param("marks") int marks);

    @Query("select e from Enrollment e where e.marks > :marks and e.grade = :grade")
    List<Enrollment> marksGreaterAndGradeEqual(@Param("marks") int marks, @Param("grade") char grade);

    @Query("select e from Enrollment e where e.student.name LIKE %:part%")
    List<Enrollment> nameContaining(@Param("part") String part);

    @Query("select COUNT(e) from Enrollment e")
    Long totalEnrollments();

    @Query("select AVG(e.marks) from Enrollment e")
    Double averageMarks();

    @Query("select MAX(e.marks) from Enrollment e")
    Integer highestMarks();
}
