package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.courseReview;
import com.example.demo.SP.CourseReviewService;

@RestController
@RequestMapping("/coursereviews")
public class CourseReviewController {
    @Autowired
    private CourseReviewService courseReviewService;

    @PostMapping("/addReview")
    public void addReview(@RequestHeader("Authorization") String token, @RequestParam int courseId, @RequestParam String reviewText, @RequestParam float rating) {
        courseReviewService.addReview(token, courseId, reviewText, rating);
    }

    @GetMapping("/getCourseReview/{courseId}")
    public List<courseReview> getReviewsForCourse(@PathVariable int courseId) {
        return courseReviewService.getReviewsForCourse(courseId);
    }

    @GetMapping("/averageRating/{courseId}")
    public double calculateAverageRating(@PathVariable int courseId) {
        return courseReviewService.calculateAverageRating(courseId);
    }
}

