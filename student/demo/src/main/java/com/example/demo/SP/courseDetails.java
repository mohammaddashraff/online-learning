package com.example.demo.SP;

import java.util.List;

import com.example.demo.Entity.courseReview;

public class courseDetails {
    private String courseName;
    private int numberOfStudents;
    private double averageRating;
    private List<courseReview> courseReviews; 
    public courseDetails(String courseName,int numberOfStudents,double averageRating,List<courseReview> courseReviews) 
    {
        this.courseName=courseName;
        this.numberOfStudents=numberOfStudents;
        this.averageRating=averageRating;
        this.courseReviews=courseReviews;
    }
    public String getCourseName() {
        return courseName;
    }
    
    public int getNumberOfStudents() {
        return numberOfStudents;
    }
    
    public double getAverageRating() {
        return averageRating;
    }
    
    public List<courseReview> getCourseReviews() {
        return courseReviews;
    }
    
}
