package com.example.jwtexemplo;

import java.util.Map;

public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();

    public void registrarCliente(String clientId, String clientSecret, Map<String, Object> permissoes) {
        // Hash da senha antes de armazenar no banco (segurança)
        String senhaHasheada = HashUtil.hashSenha(clientSecret);

        // Converter permissões para JSON string
        String permissoesJson = JsonUtil.converterParaJson(permissoes);

        // Salvar cliente no banco
        clienteDAO.salvarCliente(clientId, senhaHasheada, permissoesJson);

        System.out.println("Cliente registrado com sucesso!");
    }
}
