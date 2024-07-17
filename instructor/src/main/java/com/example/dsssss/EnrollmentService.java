package com.example.dsssss;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class EnrollmentService {
    @SpringBootClientQualifier
    @Inject
    private SpringBootAPIClient springBootAPIClient;

    public String getAllEnrollments() {
        return springBootAPIClient.getAllEnrollments();
    }

    public String acceptEnrollment(String enrollmentId) {
        return springBootAPIClient.acceptEnrollment(enrollmentId);
    }

    public String declineEnrollment(String enrollmentId) {
        return springBootAPIClient.declineEnrollment(enrollmentId);
    }
}
