package com.example.jwtexemplo;

import io.jsonwebtoken.Claims;
import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;

public class JWTSecurityContext implements SecurityContext {
    private final Claims claims;

    public JWTSecurityContext(Claims claims) {
        this.claims = claims;
    }

    @Override
    public Principal getUserPrincipal() {
        return () -> claims.getSubject(); // Define o usuário autenticado
    }

    @Override
    public boolean isUserInRole(String role) {
        return role.equals(claims.get("role", String.class)); // Obtém o papel do JWT
    }

    @Override
    public boolean isSecure() {
        return true; // Assume que a conexão é segura
    }

    @Override
    public String getAuthenticationScheme() {
        return "Bearer"; // Define o esquema de autenticação
    }
}
