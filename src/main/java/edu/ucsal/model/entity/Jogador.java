package edu.ucsal.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    private List<Coordenada> ataquesRealizados = new ArrayList<>();

    public List<Coordenada> getAtaquesRealizados() {
        return ataquesRealizados;
    }

    public String visualizarAtaques(){

        char[][] matriz = new char[10][10];

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                matriz[i][j] = '~';
            }
        }

        for(Coordenada coordenada : ataquesRealizados){

            matriz[coordenada.getY()][coordenada.getX()] = '*';
        }

        StringBuilder sb = new StringBuilder();

        sb.append("  0 1 2 3 4 5 6 7 8 9\n");

        for(int i = 0; i < 10; i++){

            sb.append(i).append(" ");

            for(int j = 0; j < 10; j++){

                sb.append(matriz[i][j]).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

}