package com.example.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.admin.Entity.Course;
import com.example.admin.Entity.Student;
import com.example.admin.Entity.admin;
import com.example.admin.Entity.instructor;
import com.example.admin.Service.InstructorService;
import com.example.admin.Service.StudentService;
import com.example.admin.Service.adminService;

@RestController
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private adminService adminService;
    @Autowired
    private RestTemplate restTemplate;
    @PostMapping("/admin/signin")
    public ResponseEntity<String> signInAdmin(@RequestBody admin admin) {
        boolean signedIn = adminService.signIn(admin.getUserName(), admin.getPassword());
        if (signedIn) {
            return ResponseEntity.ok("Admin signed in successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }

    @Autowired
    private StudentService microserviceService;

    @GetMapping("/getCourseDetails/{courseId}")
    public ResponseEntity<List> getCourseDetails(@PathVariable int courseId) {
        return microserviceService.getCourseDetails(courseId);
    }

    @PutMapping("/editCourse/{courseId}")
    public ResponseEntity<String> editCourse(@PathVariable int courseId, @RequestBody Course updatedCourse) {
        return microserviceService.editCourse(courseId, updatedCourse);
    }

    @DeleteMapping("/removeCourse/{courseId}")
    public ResponseEntity<String> removeCourse(@PathVariable int courseId) {
        return microserviceService.removeCourse(courseId);
    }

    @PostMapping("/editStudentInfo/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return microserviceService.updateStudent(id, updatedStudent);
    }
    @Autowired
    private InstructorService instructorService;

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateInstructor(@PathVariable Long id, @RequestBody instructor updatedInstructor) {
        return instructorService.updateInstructor(id, updatedInstructor);
    }
    
    @PutMapping("/courses/approve/{courseId}")
    public ResponseEntity<String> approveCourse(@PathVariable Long courseId) {
        return instructorService.approveCourse(courseId);
    }

    @PutMapping("/courses/decline/{courseId}")
    public ResponseEntity<String> declineCourse(@PathVariable Long courseId) {
        return instructorService.declineCourse(courseId);
    }
}
