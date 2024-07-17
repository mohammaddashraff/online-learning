    package com.example.demo.SP;
    import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
    import java.util.Optional;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.courseEnrollment;
    import com.example.demo.Entity.courseReview;

    @Service
    public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseEnrollmentRepository courseEnrollmentRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CourseReviewRepository couReviewRepository;
    @Autowired
    private RestTemplate restTemplate;
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    public List<Course> searchCoursesByName(String keyword) {
        return courseRepository.findByCourseNameContainingIgnoreCase(keyword);
    }

    public List<Course> searchCoursesByCategory(String category) {
        return courseRepository.findByCategoryIgnoreCase(category);
    }

    public List<Course> getCoursesSortedByRating() {
        return courseRepository.findByOrderByRatingDesc();
    }
    @Transactional(readOnly = true)
    public Optional<Course> viewCourseInformation(int courseId) {
        return courseRepository.findById(courseId);
    }

    @Transactional(readOnly = true)
    public List<courseEnrollment> getEnrollmentsForUser(int userId) {
        return courseEnrollmentRepository.findByStudentId(userId);
    }

    @Transactional
    public boolean enrollStudent(String token, int courseId) {
        try {
            DecodedJWT decodedJWT = jwtUtil.decodeToken(token);
            int studentId = decodedJWT.getClaim("id").asInt();
            if (studentId == 0) {
                return false; // Token parsing failed or student ID not found in token
            }
            Course course = courseRepository.findById(courseId).orElse(null);
            if (course == null || course.getCurrentCapacity() >= course.getMaxCapacity()) {
                return false; // Course not found or capacity full
            }
            courseEnrollment enrollment = new courseEnrollment();
            enrollment.setStudent(studentId); // Convert to integer if needed
            enrollment.setCourse(courseId);
            LocalDateTime enrollmentDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
            enrollment.setEnrollmentDate(enrollmentDateTime);
            enrollment.setStatus("pending");
            courseEnrollmentRepository.save(enrollment);
            return true; // Enrollment successful
        } catch (Exception e) {
            // Handle any exceptions here
            return false;
        }
    }

    @Transactional
    public List<courseEnrollment> getAllEnrollments(){
        return courseEnrollmentRepository.findAll();
    }


    @Transactional
    public boolean acceptEnrollment(int enrollmentId) {
        try{
        courseEnrollment enrollment = courseEnrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));
        int courseId = enrollment.getCourse();
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null || course.getCurrentCapacity() >= course.getMaxCapacity()) {
            return false; // Course not found or capacity full
        }
        course.setCurrentCapacity(course.getCurrentCapacity() + 1);
        courseRepository.save(course);
        enrollment.setStatus("Accepted");
        courseEnrollmentRepository.save(enrollment);
        return true;
    }catch (Exception e){
            // Handle any exceptions here
            return false;
        }
    }
    
    @Transactional
    public void declineEnrollment(int enrollmentId) {
        courseEnrollment enrollment = courseEnrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));
        enrollment.setStatus("Declined");
        courseEnrollmentRepository.save(enrollment);
    }

    @Transactional
    public boolean cancelEnrollment(int enrollmentId) {
        courseEnrollment enrollment = courseEnrollmentRepository.findById(enrollmentId).orElse(null);
        if (enrollment == null) {
            return false; // Enrollment not found
        }

        Course course = courseRepository.findById(enrollment.getCourse()).orElse(null);
        if (course == null) {
            return false; // Course not found
        }

        course.setCurrentCapacity(course.getCurrentCapacity() - 1);
        courseRepository.save(course);
        courseEnrollmentRepository.deleteById(enrollmentId);

        return true; // Cancellation successful
    }
    @Transactional
    public List<courseDetails> getCourseDetails(int courseId){
        Course course = courseRepository.findByCourseId(courseId);
        List<courseDetails> courseDetailsList = new ArrayList<>();
        List<courseReview> courseReviews = couReviewRepository.findAllByCourseId(courseId);
        courseDetailsList.add(new courseDetails(course.getCourseName(),course.getCurrentCapacity(),course.getRating(),courseReviews));
        return courseDetailsList;
    }
    @Transactional
public boolean editCourse(int courseId, Course updatedCourse) {
    try {
        Course existingCourse = courseRepository.findById(courseId).orElse(null);
        if (existingCourse == null) {
            return false; // Course not found
        }
        
        // Update the existing course with the new details
        existingCourse.setCourseName(updatedCourse.getCourseName());
        existingCourse.setCategory(updatedCourse.getCategory());
        existingCourse.setDescription(updatedCourse.getDescription());
        // existingCourse.setStartDate(updatedCourse.getStartDate());
        // existingCourse.setEndDate(updatedCourse.getEndDate());
        existingCourse.setInstructor(updatedCourse.getInstructor());
        existingCourse.setMaxCapacity(updatedCourse.getMaxCapacity());
        existingCourse.setRating(updatedCourse.getRating());
        existingCourse.setTotalRatings(updatedCourse.getTotalRatings());
        
        // Save the updated course
        courseRepository.save(existingCourse);
        
        return true; // Course updated successfully
    } catch (Exception e) {
        // Handle any exceptions here
        return false;
    }
}
@Transactional
public boolean removeCourse(int courseId) {
    try {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            return false; // Course not found
        }

        Course course = courseOptional.get();

        // Remove course enrollments associated with this course
        courseEnrollmentRepository.deleteByCourseId(courseId);

        // Remove course reviews associated with this course
        couReviewRepository.deleteByCourseId(courseId);

        // Delete the course itself
        courseRepository.deleteById(courseId);

        return true; // Course removed successfully
    } catch (Exception e) {
        // Handle any exceptions here
        return false;
    }
}
private final String apiUrl = "http://localhost:8080/dsssss-1.0-SNAPSHOT/api/courses/acceptedCourses";

    public void fetchAndSaveCourses() {
        Course[] courses = restTemplate.getForObject(apiUrl, Course[].class);
        if (courses != null) {
            for (Course course : courses) {
                // You can modify this logic as per your requirements
                courseRepository.save(course);
            }
        }
    }
}
