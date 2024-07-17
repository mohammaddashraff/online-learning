package com.example.demo.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "name", length = 100, nullable = true)
    private String courseName;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // @Column(name = "start_date")
    // private Date startDate;

    // @Column(name = "end_date")
    // private Date endDate;

    @Column(name = "instructor", length = 100)
    private String instructor;

    @Column(name = "max_capacity")
    private int maxCapacity;

    @Column(name = "current_capacity")
    private int currentCapacity = 0;

    @Column(name = "rating")
    private double rating = 0.0;

    @Column(name = "total_ratings")
    private int totalRatings = 0;

    // Getters
    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    // public Date getStartDate() {
    //     return startDate;
    // }

    // public Date getEndDate() {
    //     return endDate;
    // }

    public String getInstructor() {
        return instructor;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public double getRating() {
        return rating;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    // Setters
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // public void setStartDate(Date startDate) {
    //     this.startDate = startDate;
    // }

    // public void setEndDate(Date endDate) {
    //     this.endDate = endDate;
    // }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }
    
}
