package com.example.demo.SP;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.courseReview;

@Service
public class CourseReviewService {
    @Autowired
    private CourseReviewRepository courseReviewRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CourseRepository courseRepository;
    
    public void addReview(String token, int courseId, String reviewText, float rating) {
        DecodedJWT decodedJWT = jwtUtil.decodeToken(token);
        int studentId = decodedJWT.getClaim("id").asInt();

        // Get the course from the repository
        Course course = courseRepository.findByCourseId(courseId);
        if (course != null) {
            // Calculate new course rating and total ratings
            double currentRating = course.getRating();
            int totalRatings = course.getTotalRatings() + 1;
            double newRating = ((currentRating * course.getTotalRatings()) + rating) / totalRatings;

            // Update course rating and total ratings
            course.setRating(newRating);
            course.setTotalRatings(totalRatings);
            courseRepository.save(course);

            // Create and save the review
            courseReview review = new courseReview();
            review.setStudentId(studentId);
            review.setCourseId(courseId);
            review.setReviewText(reviewText);
            review.setRating(rating);
            courseReviewRepository.save(review);
        } else {
            // Handle course not found error
            throw new IllegalArgumentException("Course not found");
        }
    }


    public List<courseReview> getReviewsForCourse(int courseId) {
        return courseReviewRepository.findByCourseId(courseId);
    }

    public double calculateAverageRating(int courseId) {
        return courseReviewRepository.calculateAverageRating(courseId);
    }
}
