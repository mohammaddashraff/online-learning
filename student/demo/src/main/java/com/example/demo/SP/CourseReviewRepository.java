package com.example.demo.SP;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.courseReview;

public interface CourseReviewRepository extends JpaRepository<courseReview, Integer> {
    List<courseReview> findByCourseId(int courseId);

    @Query("SELECT AVG(r.rating) FROM courseReview r WHERE r.courseId = ?1")
    double calculateAverageRating(int courseId);
    List<courseReview> findAllByCourseId(int courseId);
    Boolean deleteByCourseId(int courseId);
}
