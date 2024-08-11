package com.microservicesproject.authentication_service.jwt_utilty;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtility {
	 //fetching the secret key from environment variable
	private final String secretKey = System.getenv("JWT_SECRET_KEY");
	
	private Claims extractClaims(String token) {
		 try {
		        return Jwts.parser()
		                .setSigningKey(secretKey)
		                .parseClaimsJws(token)
		                .getBody();
		    } catch (JwtException | IllegalArgumentException e) {
		        // Handle token parsing exceptions
		        throw new RuntimeException("Invalid token", e);
		    }
	}
	
	//create a JWT token
	public String createJwtToken(String email)
	{
		  return Jwts.builder()
		            .setSubject(email)
		            .setIssuedAt(new Date())
		            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
		            .signWith(SignatureAlgorithm.HS256, secretKey)
		            .compact();
	}
	
	
	//validate the JWT token
	public boolean validateToken(String token, String email) {
		 Claims claims = extractClaims(token);
		    return (email.equals(claims.getSubject()) &&
		            !claims.getExpiration().before(new Date()));
	}

}
