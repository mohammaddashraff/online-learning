package com.example.demo.SP;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmailAndPassword(String email, String password);
    Student findStudentById(Long id);
}