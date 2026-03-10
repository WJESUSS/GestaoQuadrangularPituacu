package com.gestaoigrejaemcelula.demo.aplication.dto;

public class MembroSelectDTO {
    private String nome;

    public MembroSelectDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
