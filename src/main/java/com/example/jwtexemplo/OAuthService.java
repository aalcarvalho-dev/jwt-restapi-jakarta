package com.example.jwtexemplo;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class OAuthService {
    private static final String SECRET_KEY = "9128349182304981029348asdlkfjasdf091234884848484";
    private ClienteDAO clienteDAO = new ClienteDAO();

/*     private static SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String gerarToken(String clientId, String clientSecret) {
        Cliente cliente = clienteDAO.buscarClientePorId(clientId);
        if (cliente != null && cliente.getClientSecret().equals(clientSecret)) {
            String token = Jwts.builder()
                .issuer("MeuServidorOAuth")
                .subject(clientId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(getSigningKey())
                .compact();
            System.out.println("Token JWT gerado: " + token);
            return token;
        }
        return null;
    } */
}
