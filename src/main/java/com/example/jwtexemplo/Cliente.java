package com.example.jwtexemplo;

import java.util.Map;

public class Cliente {
    private String clientId;
    private String clientSecret;
    private Map<String, Object> permissoes;

    public Cliente(String clientId, String clientSecret, Map<String, Object> permissoes) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.permissoes = permissoes;
    }

    public String getClientId() { return clientId; }
    public String getClientSecret() { return clientSecret; }
    public Map<String, Object> getPermissoes() { return permissoes; }
}
