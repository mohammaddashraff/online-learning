package com.example.dsssss;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
@SpringBootClientQualifier
public class SpringBootAPIClient {
    private static final String BASE_URL = "http://localhost:9093/";

    public String getAllEnrollments() {
        return sendGETRequest("courses/enrollments");
    }

    public String acceptEnrollment(String enrollmentId) {
        return sendPOSTRequest("courses/accept/" + enrollmentId);
    }
    /*
    * add course
    * 1- course course
    * 2- course API
    * 3- {}
    * 4-
    *
    * */

    public String declineEnrollment(String enrollmentId) {
        return sendPOSTRequest("courses/decline/" + enrollmentId);
    }

    private String sendGETRequest(String endpoint) {
        try {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuffer response = new StringBuffer();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                return response.toString();
            } else {
                return "Error: HTTP " + responseCode;
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    private String sendPOSTRequest(String endpoint) {
        try {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // JSON payload for PUT request (if needed)
            String payload = "{\"key\": \"value\"}";

            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuffer response = new StringBuffer();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                return response.toString();
            } else {
                return "Error: HTTP " + responseCode;
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

