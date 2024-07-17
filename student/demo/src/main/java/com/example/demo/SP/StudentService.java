package com.example.demo.SP;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Student;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public boolean signup(Student student) {
        try {
            studentRepository.save(student);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String signIn(Student student) {
        Student studentoptional = studentRepository.findByEmailAndPassword(student.getEmail(), student.getPassword());
        if (student!= null) {
                JwtUtil token = new JwtUtil();
                String generatedToken = token.generateToken(studentoptional.getId());
                return generatedToken;
        }
        return null;
    }
    public boolean updateStudent(Long id, Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setPassword(updatedStudent.getPassword());
            existingStudent.setAffiliation(updatedStudent.getAffiliation());
            existingStudent.setBio(updatedStudent.getBio());
            studentRepository.save(existingStudent);
            return true;
        } else {
            return false; // Student with given ID not found
        }
    }
}