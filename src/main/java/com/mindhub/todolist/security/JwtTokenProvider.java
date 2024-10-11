package com.mindhub.todolist.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.expiration.ms}")
    private long JWT_EXPIRATION_MS;


    // Generar JWT
    public String generateToken(Authentication authentication) {
        try {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);
            String username = authentication.getName();

            return Jwts.builder()
                    .setSubject(username)  // configura el username (o id del usuario)
                    .setIssuedAt(now)  // fecha de emisión
                    .setExpiration(expiryDate)  // fecha de expiración
                    .signWith(SignatureAlgorithm.HS512, JWT_SECRET)  // firma el token con HS512 y la clave secreta
                    .compact();
        } catch (JwtException e) {
            // capturo excepciones de JWT y lanza una personalizada
            throw new TokenGenerationException("Error al generar el token JWT", e);
        } catch (IllegalArgumentException e) {
            // captura excepciones relacionadas con argumentos inválidos y lanza una personalizada
            throw new TokenGenerationException("Argumento inválido al generar el token JWT", e);
        }
    }

    // Validar JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            // Aquí capturo las excepciones si el token no es válido
            return false;
        }
    }

    // Obtener el username a partir del JWT
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
