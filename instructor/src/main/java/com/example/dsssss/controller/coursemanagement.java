package com.example.dsssss.controller;

import com.example.dsssss.Entity.Course;
import com.example.dsssss.Entity.Review;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Stateful
@Path("/courses")
public class coursemanagement {
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;
    @POST
    @Path("/addcourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCourse(Course course,@Context HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Long instructorId = (Long) session.getAttribute("InstructorId");
            course.setInstructorId(instructorId);
            entityManager.persist(course);
            return Response.ok(course).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create course: " + e.getMessage()).build();
        }
    }


    @GET
    @Path("/details/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourseDetails(@PathParam("courseId") Long courseId) {
        // Logic to retrieve detailed information about the course with the given ID
        Course course = entityManager.find(Course.class, courseId);
        if (course != null) {
            return Response.ok(course).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Course not found").build();
        }
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCourses(@QueryParam("name") String name,
                                  @QueryParam("category") String category,
                                  @QueryParam("sortByRatings") boolean sortByRatings) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root<Course> root = criteriaQuery.from(Course.class);

        // Create predicates based on search criteria
        Predicate predicate = criteriaBuilder.conjunction();
        if (name != null && !name.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }
        if (category != null && !category.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category"), category));
        }

        // Add sorting by ratings if requested
        if (sortByRatings) {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("ratings")));
        }

        criteriaQuery.where(predicate);

        TypedQuery<Course> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Course> courses = typedQuery.getResultList();

        if (!courses.isEmpty()) {
            return Response.ok(courses).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No courses found").build();
        }
    }
    @GET
    @Path("/{courseId}/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listReviewsForCourse(@PathParam("courseId") Long courseId) {
        try {
            List<Review> reviews = entityManager.createQuery(
                            "SELECT r FROM Review r WHERE r.course.id = :courseId", Review.class)
                    .setParameter("courseId", courseId)
                    .getResultList();
            return Response.ok(reviews).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch reviews for course: " + e.getMessage()).build();
        }
    }
    @GET
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewDetailedInformation(@PathParam("courseId") Long courseId) {
        try {
            Course course = entityManager.find(Course.class, courseId);
            if (course != null) {
                return Response.ok(course).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Course not found").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch course information: " + e.getMessage()).build();
        }
    }
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response searchCourses(@QueryParam("name") String name, @QueryParam("category") String category) {
//        try {
//            List<Course> courses = entityManager.createQuery(
//                            "SELECT c FROM Course c WHERE c.courseName LIKE :name AND c.category = :category", Course.class)
//                    .setParameter("name", "%" + name + "%")
//                    .setParameter("category", category)
//                    .getResultList();
//            return Response.ok(courses).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .entity("Failed to search courses: " + e.getMessage()).build();
//        }
//    }
    @GET
    @Path("/acceptedCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAcceptedCourses() {
        try {
            List<Course> acceptedCourses = entityManager.createQuery(
                            "SELECT c FROM Course c WHERE c.status = 'accepted'", Course.class)
                    .getResultList();
            return Response.ok(acceptedCourses).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch accepted courses: " + e.getMessage()).build();
        }
    }
    @PUT
    @Path("/accept/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response acceptCourse(@PathParam("courseId") Long courseId) {
        try {
            Course course = entityManager.find(Course.class, courseId);
            if (course != null) {
                course.setStatus("accepted");
                entityManager.merge(course);
                return Response.ok(course).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Course not found").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to accept course: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/decline/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response declineCourse(@PathParam("courseId") Long courseId) {
        try {
            Course course = entityManager.find(Course.class, courseId);
            if (course != null) {
                course.setStatus("declined");
                entityManager.merge(course);
                return Response.ok(course).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Course not found").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to decline course: " + e.getMessage()).build();
        }
    }

}
