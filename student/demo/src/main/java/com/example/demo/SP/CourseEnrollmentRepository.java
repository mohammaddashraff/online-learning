package com.example.demo.SP;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.courseEnrollment;
 
public interface CourseEnrollmentRepository extends JpaRepository<courseEnrollment, Integer> {
    List<courseEnrollment> findByStudentIdAndCourseId(int studentId, int courseId);
    List<courseEnrollment> findByStudentId(int studentId);
   //List<courseEnrollment> findByenrollmentId(Long enrollmentId);
    List<courseEnrollment> findAll();
    Boolean deleteByCourseId(int courseId);
}