package com.ranjith.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;

@Service
@Data
public class JwtUtils {

	@Value("${jwt.secret}")
	private String secretKey;
	@Value("${jwt.expiration}")
	private long expirationTime;
	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(getSigningKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		return extractUsername(token).equals(userDetails.getUsername()) 
				&& !isTokenExpired(token);
	}
	
	public boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}
	
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	private Claims extractAllClaims(String token) {
		JwtParser jwtParser = Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build();
		Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
		return claimsJws.getBody();
	}
	
	
}
