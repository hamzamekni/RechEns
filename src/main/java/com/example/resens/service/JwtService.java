package com.example.resens.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtService {
    @Value("${jwt.secret}")
    private  String SECRET_KEY;

    private static final long DEFAULT_EXPIRATION_TIME_MILLIS = 604800000;
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String genToken(UserDetails userDetails , Map<String , String> map){
        return generateToken(map, userDetails, 1000*60*24);
    }

    public String generateToken(
            Map<String, String> extraClaims,
            UserDetails userDetails, long expirationTimeMillis ){
        Date issuedAt = new Date();
        Date expirationDate = new Date(issuedAt.getTime() + expirationTimeMillis);
        System.out.println("Expiration Date: " + expirationDate);

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String refreshExpiredToken(String expiredToken) {
        Claims claims = extractAllClaims(expiredToken);
        Date originalExpirationDate = claims.getExpiration();

        // Set the new expiration date
        Date newExpirationDate = new Date(originalExpirationDate.getTime() + DEFAULT_EXPIRATION_TIME_MILLIS);


        // Generate the new token
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(newExpirationDate)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    public boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }
    public Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
