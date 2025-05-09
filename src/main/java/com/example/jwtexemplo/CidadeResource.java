package com.example.jwtexemplo;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/cidades")
@Produces(MediaType.APPLICATION_JSON)
public class CidadeResource {
    @GET
    public List<Cidade> listarCidades() {
        System.out.println("chegou");
        return List.of(
                new Cidade("São Paulo", "SP"),
                new Cidade("Curitiba", "PR"),
                new Cidade("Rio de Janeiro", "RJ"));
    }
}