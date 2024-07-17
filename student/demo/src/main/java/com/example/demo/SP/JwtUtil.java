package com.example.demo.SP;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;



@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private String secret = "kklfojl";

    public String generateToken(long id) throws IllegalArgumentException {
        try {
            return JWT.create()
                    .withClaim("id", id)
                    .sign(Algorithm.HMAC256(secret));
        } catch (Exception e) {
            logger.error("Error generating token: {}", e.getMessage());
            throw e;
        }
    }


    public DecodedJWT decodeToken(String token) throws JWTDecodeException, IllegalArgumentException {
        try {
            // Split the token string to extract the actual JWT token
            String[] parts = token.split(" ");
            if (parts.length != 2 || !parts[0].equals("Bearer")) {
                throw new IllegalArgumentException("Invalid token format");
            }
            token = parts[1];
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            System.out.println(verifier.verify(token));
            return verifier.verify(token);

        } catch (JWTDecodeException | IllegalArgumentException e) {
            logger.error("Error decoding token: {}", e.getMessage());
            throw e;
        }
    }
}
