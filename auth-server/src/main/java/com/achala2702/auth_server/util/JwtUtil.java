package com.achala2702.auth_server.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    private SecretKey jwtSecretKey;

    @PostConstruct
    public void init() {
        if(jwtSecret == null || jwtSecret.isBlank()) {
            throw new RuntimeException("JWT Secret is Empty..!");
        }
        jwtSecretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateJwt(String email) {

        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + 15 * 60 *1000);

        return Jwts.builder()
                .subject(email)
                .issuedAt(currentDate)
                .expiration(expirationDate)
                .signWith(jwtSecretKey)
                .compact();
    }

    public Claims validateJwt(String token) {
        return Jwts.parser()
                .verifyWith(jwtSecretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
