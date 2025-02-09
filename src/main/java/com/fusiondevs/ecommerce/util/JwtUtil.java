package com.fusiondevs.ecommerce.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    // Clave secreta para firmar y validar el token.
    // En producción, debe mantenerse segura y configurarse externamente.
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    // Extrae todas las claims (información) del token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // Extrae el username (subject) del token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Extrae la fecha de expiración del token
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    // Indica si el token está expirado
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Valida que el token corresponda al username y que no esté expirado
    public Boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Opcional: Genera un token (útil para pruebas o generación desde el backend)
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
