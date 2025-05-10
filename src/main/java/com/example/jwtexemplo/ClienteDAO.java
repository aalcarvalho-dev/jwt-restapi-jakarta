package com.example.jwtexemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class ClienteDAO {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/garhws";
    private static final String USER = "sa_garh";
    private static final String PASS = "stranger";

    public void salvarCliente(String clientId, String clientSecret, String permissoesJson) {
        String sql = "INSERT INTO clientes_api (client_id, client_secret, permissoes) VALUES (?, ?, ?::json)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clientId);
            stmt.setString(2, clientSecret);
            stmt.setString(3, permissoesJson);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cliente buscarClientePorId(String clientId) {
        String sql = "SELECT * FROM clientes_api WHERE client_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Map<String, Object> permissoes = new HashMap<>(); // Processar JSON corretamente
                return new Cliente(rs.getString("client_id"), rs.getString("client_secret"), permissoes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
