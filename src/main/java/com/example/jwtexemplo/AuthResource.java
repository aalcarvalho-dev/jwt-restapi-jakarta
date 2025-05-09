package com.example.jwtexemplo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
    private static final Map<String, String> USUARIOS = new HashMap<>();
    static {
        USUARIOS.put("admin", "1234"); // Usuário e senha fictícios
    }

    @POST
    public Response autenticar(@QueryParam("usuario") String usuario,
            @QueryParam("senha") String senha) {
        System.out.println("entrou");
        if (USUARIOS.containsKey(usuario) && USUARIOS.get(usuario).equals(senha)) {
            String token = JWTUtil.gerarToken(usuario);
            return Response.ok("{\"token\": \"" + token + "\"}").build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\": \"Credenciais inválidas\"}").build();
    }
}