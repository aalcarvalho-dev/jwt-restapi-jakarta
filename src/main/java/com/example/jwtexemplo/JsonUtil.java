package com.example.jwtexemplo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class JsonUtil {
    public static String converterParaJson(Map<String, Object> permissoes) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(permissoes);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
}