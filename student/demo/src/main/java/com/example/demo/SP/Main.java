// import java.sql.SQLException;
// import java.util.List;

// public class Main {
//     public static void main(String[] args) throws SQLException {
//        try(var connection = DB.connect()){
//            System.out.println("Connected");
//        }
//        student student = new student("jana","jana@gmail.com","jana","jana","janjon");
//        studentManager studentManager = new studentManager();
//        //studentManager.signup(student);
//        studentManager.singIn("jana@gmail.com","jana");
//        courseManager courseManager = new courseManager();
//         List<course> coursesByName = courseManager.searchCourses("logic", "name");
//         printCourses(coursesByName);
//        //courseManager.enrollStudent(1,1);
//         //courseManager.cancelEnrollment(1);
//         List<course> courseEnrollment = courseManager.getEnrollmentsForUser(1);
//         printCourses(courseEnrollment);
//         courseReviewManager reviewManager = new courseReviewManager();

//         // Add a review for a course (assuming student ID 1, course ID 1)
//         reviewManager.addReview(1, 1, "Great course!", 5);

//         // Get reviews for a course (assuming course ID 1)
//         List<courseReview> reviewsForCourse1 = reviewManager.getReviewsForCourse(1);
//         for (courseReview review : reviewsForCourse1) {
//             System.out.println("Student ID: " + review.getStudentId());
//             System.out.println("Rating: " + review.getRating());
//             System.out.println("Review: " + review.getReviewText());
//             System.out.println("Review Date: " + review.getReviewDate());
//             System.out.println();
//         }

//         // Calculate average rating for a course (assuming course ID 1)
//         double averageRatingCourse1 = reviewManager.calculateAverageRating(1);
//         System.out.println("Average Rating for Course 1: " + averageRatingCourse1);
//     }
//     private static void printCourses(List<course> courses) {
//         for (course course : courses) {
//             System.out.println("Course ID: " + course.getCourseId());
//             System.out.println("Name: " + course.getCourseName());
//             System.out.println("Category: " + course.getCategory());
//             System.out.println("Description: " + course.getDescription());
//             System.out.println("Start Date: " + course.getStartDate());
//             System.out.println("End Date: " + course.getEndDate());
//             System.out.println("Instructor: " + course.getInstructor());
//             System.out.println("Max Capacity: " + course.getMaxCapacity());
//             System.out.println("Current Capacity: " + course.getCurrentCapacity());
//             System.out.println("Rating: " + course.getRating());
//             System.out.println("Total Ratings: " + course.getTotalRating());
//             System.out.println("Enrollmenet status: " + course.getStatus());
//             System.out.println("--------------------------------------");
//         }
//     }

// }