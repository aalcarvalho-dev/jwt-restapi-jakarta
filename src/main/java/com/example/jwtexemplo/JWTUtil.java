package com.example.jwtexemplo;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JWTUtil {
    private static final String SECRET_KEY = "9128349182304981029348asdlkfjasdf091234884848484"; // Alterar para uma chave
                                                                                             // segura
    private static final long EXPIRATION_TIME = 3600000; // 1 hora

    private static SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String gerarToken(String usuario) {
        return Jwts.builder()
                .subject(usuario)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    public static Claims validarToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey()) // Agora recebe um SecretKey
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static String gerarTokenAplicacao() {
        return Jwts.builder()
            .issuer("MinhaAplicacao")  // 🔹 Nome da aplicação
            .subject("app_token") // 🔹 Identificação como token de aplicação
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 3600000)) // 🔹 Expira em 1 hora
            .signWith(getSigningKey())
            .compact();
    }

}
