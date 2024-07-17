package com.example.admin.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.admin.Entity.instructor;


@Service
public class InstructorService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> updateInstructor(Long id, instructor updatedInstructor) {
        String apiUrl = "http://localhost:8080/dsssss-1.0-SNAPSHOT/api/instructor/update/" + id; // Replace with your API URL

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<instructor> requestEntity = new HttpEntity<>(updatedInstructor, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
            apiUrl,
            HttpMethod.PUT,
            requestEntity,
            String.class
        );

        return responseEntity;
    }
    public ResponseEntity<String> approveCourse(Long courseId) {
        String apiUrl = "http://localhost:8080/dsssss-1.0-SNAPSHOT/api/courses/accept/" + courseId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Void> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
            apiUrl,
            HttpMethod.PUT,
            requestEntity,
            String.class
        );

        return responseEntity;
    }

    public ResponseEntity<String> declineCourse(Long courseId) {
        String apiUrl = "http://localhost:8080/dsssss-1.0-SNAPSHOT/api/courses/decline/" + courseId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Void> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
            apiUrl,
            HttpMethod.PUT,
            requestEntity,
            String.class
        );

        return responseEntity;
    }
}