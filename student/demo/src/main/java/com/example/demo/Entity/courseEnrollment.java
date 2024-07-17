package com.example.demo.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course_enrollments")
public class courseEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollmentId;


    private int studentId;


    private int courseId;

    private LocalDateTime enrollmentDate;

    private String status;

    // Getters and setters

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getStudent() {
        return studentId;
    }

    public void setStudent(int student) {
        this.studentId = student;
    }

    public int getCourse() {
        return courseId;
    }

    public void setCourse(int course) {
        this.courseId = course;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime dateTime) {
        this.enrollmentDate = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
