package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.courseEnrollment;
import com.example.demo.SP.CourseService;
import com.example.demo.SP.courseDetails;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/allCourses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/getCoursebyId/{courseId}")
    public Course getCourseById(@PathVariable int courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/searchByName")
    public List<Course> searchCoursesByName(@RequestParam String keyword) {
        return courseService.searchCoursesByName(keyword);
    }

    @GetMapping("/searchByCategory")
    public List<Course> searchCoursesByCategory(@RequestParam String category) {
        return courseService.searchCoursesByCategory(category);
    }

    @GetMapping("/sortedByRating")
    public List<Course> getCoursesSortedByRating() {
        return courseService.getCoursesSortedByRating();
    }

    @GetMapping("/viewCourseInfo/{courseId}")
    public Optional<Course> viewCourseInformation(@PathVariable int courseId) {
        return courseService.viewCourseInformation(courseId);
    }

    @GetMapping("/getEnrollments/{userId}")
    public List<courseEnrollment> getEnrollmentsForUser(@PathVariable int userId) {
        return courseService.getEnrollmentsForUser(userId);
    }

    @PostMapping("/enroll")
    public String enrollStudent(@RequestParam int courseId,@RequestHeader("Authorization") String token) {

        // Enroll the student using the token and courseId
        boolean enrolled = courseService.enrollStudent(token, courseId);
        if (enrolled) {
            return "Enrollment successful";
        } else {
            return "Enrollment failed";
        }
    }

    @PostMapping("/accept/{enrollmentId}")
    public ResponseEntity<String> acceptEnrollment(@PathVariable int enrollmentId) {
        boolean accepted = courseService.acceptEnrollment(enrollmentId);
        if (accepted) {
            return ResponseEntity.ok("Enrollment accepted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Enrollment not found or course capacity full.");
        }
    }
    
    @PostMapping("/decline/{enrollmentId}")
    public ResponseEntity<String> declineEnrollment(@PathVariable int enrollmentId) {
        courseService.declineEnrollment(enrollmentId);
        return ResponseEntity.ok("Enrollment declined successfully.");
    }

    @GetMapping("/enrollments")
    public ResponseEntity<List<courseEnrollment>> getAllEnrollments() {
        List<courseEnrollment> enrollments = courseService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    @DeleteMapping("/cancelEnrollment/{enrollmentId}")
    public String cancelEnrollment(@PathVariable int enrollmentId) {
        boolean canceled = courseService.cancelEnrollment(enrollmentId);
        if (canceled) {
            return "Cancellation successful";
        } else {
            return "Cancellation failed";
        }
    }
    @GetMapping("/getCourseDetails/{courseId}")
    public ResponseEntity<List<courseDetails>> getCourseDetails(@PathVariable int courseId) {
        List<courseDetails> courseDetailsList = courseService.getCourseDetails(courseId);
        if (courseDetailsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(courseDetailsList);
        }
    }
    @PostMapping("/editCourse/{courseId}")
    public ResponseEntity<String> editCourse(@PathVariable int courseId, @RequestBody Course updatedCourse) {
    boolean edited = courseService.editCourse(courseId, updatedCourse);
    if (edited) {
        return ResponseEntity.ok("Course updated successfully.");
    } else {
        return ResponseEntity.notFound().build(); // Course with given ID not found
    }
}
@DeleteMapping("/removeCourse/{courseId}")
public ResponseEntity<String> removeCourse(@PathVariable int courseId) {
    boolean removed = courseService.removeCourse(courseId);
    if (removed) {
        return ResponseEntity.ok("Course removed successfully.");
    } else {
        return ResponseEntity.notFound().build(); // Course with given ID not found
    }
}
    @GetMapping("/fetchAndSave")
    public ResponseEntity<String> fetchAndSaveCourses() {
        courseService.fetchAndSaveCourses();
        return ResponseEntity.ok("Courses fetched and saved successfully.");
    }
}
