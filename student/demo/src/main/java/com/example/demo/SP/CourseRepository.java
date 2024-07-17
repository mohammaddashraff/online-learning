package com.example.demo.SP;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.example.demo.Entity.Course;

// public interface CourseRepository extends JpaRepository<Course, Integer> {
//     List<Course> findByCourseNameContainingIgnoreCaseOrderByRating(String searchQuery);
//     List<Course> findByCourseNameContainingIgnoreCaseOrderByName(String searchQuery);
// }
// CourseRepository.java
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByCourseNameContainingIgnoreCase(String keyword);
    List<Course> findByCategoryIgnoreCase(String category);
    List<Course> findByOrderByRatingDesc();
    Course findByCourseId(int courseId);
}

