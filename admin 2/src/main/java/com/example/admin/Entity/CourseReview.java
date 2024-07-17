package com.example.admin.Entity;

public class CourseReview {
    private Long reviewId;
    private Long studentId;
    private int courseId;
    private String reviewText;
    private double rating;

    public CourseReview() {
    }

    public CourseReview(Long reviewId, Long studentId, int courseId, String reviewText, double rating) {
        this.reviewId = reviewId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    // Getters and setters
    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
