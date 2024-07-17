package com.example.admin.Entity;

public class Course {
    private int courseId;
    private String courseName;
    private String category;
    private int maxCapacity;
    private int currentCapacity;

    public Course() {
    }

    public Course(int courseId, String courseName, String category, int maxCapacity, int currentCapacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.category = category;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    // Getters and setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
}
