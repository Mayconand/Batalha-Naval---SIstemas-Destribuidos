package edu.ucsal.model.entity;

import java.io.Serializable;

public class Jogador implements Serializable{

    private String nome;
    private Tabuleiro tabuleiro;

    public Jogador(String nome) {
        this.nome = nome;
        this.tabuleiro = new Tabuleiro();
    }

    public String getNome() {
        return nome;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}