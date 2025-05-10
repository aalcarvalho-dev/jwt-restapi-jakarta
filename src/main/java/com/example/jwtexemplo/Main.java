package com.example.jwtexemplo;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.mindrot.jbcrypt.BCrypt;

public class Main {
    private static final String BASE_URI = "http://localhost:8080/";

    public static HttpServer startServer() {
        final ResourceConfig config = new ResourceConfig()
                .packages("com.example.jwtexemplo")
                .register(JacksonFeature.class);
        // estão os
        // recursos REST
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    public static void main(String[] args) {

        ClienteService clienteService = new ClienteService();

        // Definir permissões do cliente
        Map<String, Object> permissoes = new HashMap<>();
        permissoes.put("acesso", "LEITURA");
        permissoes.put("limite_requisicoes", 1000);

        // Registrar novo cliente
        // clienteService.registrarCliente("app_cliente_123", "senhaSuperSegura",
        // permissoes);

        String senhaHasheada = BCrypt.hashpw("senhaSuperSegura", BCrypt.gensalt());
        System.out.println(senhaHasheada);

        final HttpServer server = startServer();
        System.out.println("Servidor iniciado em " + BASE_URI);
        System.out.println(JWTUtil.gerarToken("admin"));
        System.out.println(JWTUtil.gerarTokenAplicacao());
        try {
            Thread.currentThread().join(); // Mantém o servidor rodando
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}