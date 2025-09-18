package com.example.Spring_Security.Utils;

//import java.awt.RenderingHints.Key;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final String SECRET="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk";
	private final long EXPIRATION=1000*60*60;
	
	private final Key secrectKey=(Key) Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
	
	// Generate token
	public  String generateTokens(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
				.signWith( secrectKey,SignatureAlgorithm.HS256)
				.compact();
	}
	//Extract claims (reusable)
	public String extractEmail(String token) {
		return Jwts.parserBuilder()
		.setSigningKey(secrectKey)
		.build()
		.parseClaimsJws(token)
		.getBody()
		.getSubject();
	}
	public boolean validateJwtToken(String token) {
		try {
			extractEmail(token);
			return true;
		} 
		catch (JwtException exception) {
			return false;
		}
	}
	
}
