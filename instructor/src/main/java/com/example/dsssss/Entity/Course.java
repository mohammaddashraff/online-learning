package com.example.dsssss.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    private Long instructorId;

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseName;
    private String category;
    private String description;
//    private LocalDate startDate;
//    private LocalDate endDate;
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'pending'")
    private String status = "pending";
    public int getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

//    public LocalDate getEndDate() {
//        return endDate;
//    }

//    public void setEndDate(LocalDate endDate) {
//        this.endDate = endDate;
//    }

//    public LocalDate getStartDate() {
//        return startDate;
//    }

//    public void setStartDate(LocalDate startDate) {
//        this.startDate = startDate;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String name) {
        this.courseName = name;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    private String instructor;
    private int maxCapacity;
    private int currentCapacity;
    private double rating;
    private int totalRatings;

    public void setDuration(String duration) {

    }

    public void setCapacity(int capacity) {
    }
}
