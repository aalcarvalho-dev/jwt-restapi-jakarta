package com.example.jwtexemplo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cidade {
    @JsonProperty("nome")
    private String nome;
    
    @JsonProperty("estado")
    private String estado;


    public Cidade(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }
}
