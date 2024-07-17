package com.example.dsssss;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

// Import JWT library for token generation and validation

// Define a secret key for signing JWTs (keep it secure)
public class TokenManager {
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Method to generate a JWT token
    public static String generateToken(long instructorId) {
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30); // Token expires in 30 minutes
        Date expirationDate = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setSubject(Long.toString(instructorId))
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY)
                .compact();
    }

    // Method to validate and extract instructorId from a JWT token
    public static Long validateToken(String token) {
        try {
            return Long.parseLong(
                    Jwts.parser().setSigningKey(SECRET_KEY).build()
                            .parseClaimsJws(token)
                            .getBody()
                            .getSubject()
            );
        } catch (Exception e) {
            return null; // Invalid token
        }
    }
}

// Update your login method to generate and return a JWT token
