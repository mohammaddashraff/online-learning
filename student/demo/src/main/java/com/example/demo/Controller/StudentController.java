package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Student;
import com.example.demo.SP.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/signup")
    public boolean signup(@RequestBody Student student) {
        return studentService.signup(student);
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody Student student) {
        return studentService.signIn(student);
    }
    
    @PostMapping("/editStudentInfo/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        boolean updated = studentService.updateStudent(id, updatedStudent);
        if (updated) {
            return ResponseEntity.ok("Student updated successfully.");
        } else {
            return ResponseEntity.notFound().build(); // Student with given ID not found
        }
    }
}
