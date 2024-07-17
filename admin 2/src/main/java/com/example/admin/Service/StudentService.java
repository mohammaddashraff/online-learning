package com.example.admin.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.admin.Entity.Course;
import com.example.admin.Entity.Student;

@Service
public class StudentService {

    private final String baseUrl = "http://localhost:9093/"; // Replace with actual URL

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<List> getCourseDetails(int courseId) {
        String url = baseUrl + "courses/getCourseDetails/{courseId}";
        return restTemplate.getForEntity(url, List.class, courseId);
    }

    public ResponseEntity<String> editCourse(int courseId, Course updatedCourse) {
        String url = baseUrl + "courses/editCourse/{courseId}";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, updatedCourse, String.class, courseId);
        return responseEntity;
    }

    public ResponseEntity<String> removeCourse(int courseId) {
        String url = baseUrl + "courses/removeCourse/{courseId}";
        return restTemplate.exchange(url, HttpMethod.DELETE, null, String.class, courseId);
    }

    public ResponseEntity<String> updateStudent(Long id, Student updatedStudent) {
        String url = baseUrl + "students/editStudentInfo/{id}";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, updatedStudent, String.class, id);
        return responseEntity;
    }
}
