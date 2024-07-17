package com.example.admin.Entity;

import java.util.List;

public class CourseDetails {
    private String courseName;
    private int currentCapacity;
    private double rating;
    private List<CourseReview> courseReviews;

    public CourseDetails() {
    }

    public CourseDetails(String courseName, int currentCapacity, double rating, List<CourseReview> courseReviews) {
        this.courseName = courseName;
        this.currentCapacity = currentCapacity;
        this.rating = rating;
        this.courseReviews = courseReviews;
    }

    // Getters and setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<CourseReview> getCourseReviews() {
        return courseReviews;
    }

    public void setCourseReviews(List<CourseReview> courseReviews) {
        this.courseReviews = courseReviews;
    }
}
