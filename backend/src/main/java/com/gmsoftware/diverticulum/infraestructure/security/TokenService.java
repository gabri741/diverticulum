package com.gmsoftware.diverticulum.infraestructure.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gmsoftware.diverticulum.domain.model.Usuario;

@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;
    
    public String generateToken(Usuario user) {
        
        try {
            Algorithm algo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("diverticulum-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpiratiionDate())
                    .sign(algo);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro na autenticação;");
        }
    }
    
    public String validateToken(String token) {
        try {
            Algorithm algo = Algorithm.HMAC256(secret);
            return JWT.require(algo)
                    .withIssuer("diverticulum-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
    
    private Instant generateExpiratiionDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
    
}
