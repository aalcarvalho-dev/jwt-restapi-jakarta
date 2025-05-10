package com.example.jwtexemplo;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Priority;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(1)
public class JWTAuthFilter implements ContainerRequestFilter {
    private static final String SECRET_KEY = "9128349182304981029348asdlkfjasdf091234884848484";

    @Override
    public void filter(ContainerRequestContext requestContext) {

        String path = requestContext.getUriInfo().getPath(); // Obtém o caminho da requisição

        // Permitir acesso direto ao endpoint de obtenção de token OAuth
        if (path.equals("oauth/token")) {
            return; // Ignora o filtro e permite a requisição
        }

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("error", "Token não fornecido ou inválido")
                    .add("status", Status.UNAUTHORIZED.getStatusCode())
                    .build();

            requestContext.abortWith(Response.status(Status.UNAUTHORIZED)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build());

            return;
/*             try {
                JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("error", "Token inválido ou expirado")
                    .add("status", Response.Status.UNAUTHORIZED.getStatusCode())
                    .build();
            
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build());
            } catch (Exception e) {
                e.printStackTrace(); // Log para depuração
                requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Erro interno ao processar resposta\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
            } */
        }
        String token = authHeader.substring("Bearer ".length());
        try {
            /* funcionando com usuario e senha */
            Claims claims = JWTUtil.validarToken(token);
            requestContext.setSecurityContext(new JWTSecurityContext(claims));

            /*
             * Claims claims = Jwts.parser()
             * .verifyWith(getSigningKey())
             * .build()
             * .parseSignedClaims(token)
             * .getPayload();
             * 
             * if (!"app_token".equals(claims.getSubject())) { // 🔹 Apenas tokens da
             * aplicação são aceitos
             * requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
             * }
             */

        } catch (Exception e) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("error", "Token inválido ou expirado")
                    .add("status", Status.UNAUTHORIZED.getStatusCode())
                    .build();

            requestContext.abortWith(Response.status(Status.UNAUTHORIZED)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build());

        }
    }

    private static SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
