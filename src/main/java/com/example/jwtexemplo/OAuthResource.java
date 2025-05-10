package com.example.jwtexemplo;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/oauth/token")
@Produces(MediaType.APPLICATION_JSON)
public class OAuthResource {
    private static final String SECRET_KEY = "9128349182304981029348asdlkfjasdf091234884848484";
    private ClienteDAO clienteDAO = new ClienteDAO();

    private static SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response gerarToken(ClientAuthRequest request) {
        Cliente cliente = clienteDAO.buscarClientePorId(request.getClientId());

        if (cliente != null && HashUtil.verificarSenha(request.getClientSecret(), cliente.getClientSecret())) {
            String token = Jwts.builder()
                    .issuer("MeuServidorOAuth")
                    .subject(request.getClientId())
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 3600000)) // Expira em 1 hora
                    .signWith(getSigningKey())
                    .compact();

            System.out.println("Token JWTTTT gerado: " + token);


            return Response.ok("{\"access_token\":\"" + token + "\", \"expires_in\":3600}").build();
        }else{
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\":\"Credenciais inválidas !!\"}").build();
        }
    }
}
