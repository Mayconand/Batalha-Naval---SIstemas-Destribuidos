package edu.ucsal.model.entity;

import java.io.Serializable;

import edu.ucsal.enums.ResultadoAtaque;


public class Partida implements Serializable{

    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador turnoAtual;

    public Partida(Jogador jogador1, Jogador jogador2) {

        this.jogador1 = jogador1;
        this.jogador2 = jogador2;

        this.turnoAtual = jogador1;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public Jogador getTurnoAtual() {
        return turnoAtual;
    }

    public void trocarTurno(){

        if(turnoAtual == jogador1){
            turnoAtual = jogador2;
        }else{
            turnoAtual = jogador1;
        }
    }

    public boolean verificarVitoria(Jogador jogador){

        Jogador adversario;

        if(jogador == jogador1){
            adversario = jogador2;
        }else{
            adversario = jogador1;
        }

        for(Barco barco : adversario.getTabuleiro().getBarcos()){

            if(!barco.barcoAfundou()){
                return false;
            }
        }

        return true;
    }

    public ResultadoAtaque atacar(Jogador atacante, Coordenada coordenada){

        if(atacante != turnoAtual){
            return ResultadoAtaque.FORA_DO_TURNO;
        }

        Jogador adversario;

        if(atacante == jogador1){
            adversario = jogador2;
        }else{
            adversario = jogador1;
        }

        ResultadoAtaque resultado =
                adversario.getTabuleiro().receberAtaque(coordenada);

        if(!verificarVitoria(atacante)){
            trocarTurno();
        }

        return resultado;
    }
}