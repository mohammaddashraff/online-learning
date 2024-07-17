package com.example.dsssss.controller;
import com.example.dsssss.Entity.instructor;
import com.example.dsssss.TokenManager;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.io.IOException;

@Stateless
@Path("/instructor")
public class instructormanagement {
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;
@POST
@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response login(instructor loginRequest) {
    String email = loginRequest.getEmail();
    String password = loginRequest.getPassword();

    // Check if credentials are valid
    instructor inst = validateCredentials(email, password);
    if (inst != null) {
        // Generate JWT token
        String token = TokenManager.generateToken(inst.getId());

        // Return token in response
        return Response.ok().entity(token).build();
    } else {
        return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid email/password").build();
    }
}

    // Modify your getId method to extract instructorId from the JWT token
    @GET
    @Path("/getId")
    public Response getId(@CookieParam("jwt") String token) {
        if (token != null) {
            Long instructorId = TokenManager.validateToken(token);
            if (instructorId != null) {
                return Response.ok("Instructor ID: " + instructorId).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
    }
    private instructor validateCredentials(String email, String password) {
        try {
            return entityManager.createQuery(
                            "SELECT i FROM instructor i WHERE i.email = :email AND i.password = :password", instructor.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // No instructor found with provided credentials
        }
    }
    @POST
    @Path("/signUp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(instructor instructor){
        try {
            entityManager.persist(instructor);
            return Response.ok(instructor).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to register : " + e.getMessage()).build();
        }
    }
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInstructor(@PathParam("id") Long id, instructor updatedInstructor) {
        instructor existingInstructor = entityManager.find(instructor.class, id);
        if (existingInstructor != null) {
            existingInstructor.setName(updatedInstructor.getName());
            existingInstructor.setEmail(updatedInstructor.getEmail());
            existingInstructor.setPassword(updatedInstructor.getPassword());
            existingInstructor.setAffiliation(updatedInstructor.getAffiliation());
            existingInstructor.setYearsOfExperience(updatedInstructor.getYearsOfExperience());
            existingInstructor.setBio(updatedInstructor.getBio());
            entityManager.merge(existingInstructor);
            return Response.ok(existingInstructor).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Instructor with ID " + id + " not found").build();
        }
    }
}
