package com.example.dsssss.controller;

import com.example.dsssss.EnrollmentService;
import com.example.dsssss.SpringBootAPIClient;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/enrollments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class enrollmentController {


    @EJB
    private EnrollmentService enrollmentService;

    @GET
    @Path("/all")
    public String getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @POST
    @Path("/accept/{enrollmentId}")
    public String acceptEnrollment(@PathParam("enrollmentId") String enrollmentId) {
        return enrollmentService.acceptEnrollment(enrollmentId);
    }

    @POST
    @Path("/decline/{enrollmentId}")
    public String declineEnrollment(@PathParam("enrollmentId") String enrollmentId) {
        return enrollmentService.declineEnrollment(enrollmentId);
    }
}