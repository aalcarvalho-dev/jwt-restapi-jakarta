package com.example.jwtexemplo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestServer {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            System.out.println("Código de resposta: " + responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}