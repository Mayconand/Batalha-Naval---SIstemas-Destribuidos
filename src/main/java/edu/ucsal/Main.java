package edu.ucsal;

import edu.ucsal.enums.Direcao;
import edu.ucsal.enums.ResultadoAtaque;
import edu.ucsal.enums.TipoBarco;


import edu.ucsal.model.entity.Barco;
import edu.ucsal.model.entity.Coordenada;
import edu.ucsal.model.entity.Jogador;
import edu.ucsal.model.entity.Partida;

public class Main {

    public static void main(String[] args) {

        Jogador jogador1 = new Jogador("Maycon");
        Jogador jogador2 = new Jogador("Raissa");

        Partida partida = new Partida(jogador1, jogador2);

        Barco barco = new Barco(
                new Coordenada(2,3),
                TipoBarco.SUBMARINO,
                Direcao.HORIZONTAL
        );

        jogador2.getTabuleiro().adicionarBarco(barco);

        ResultadoAtaque resultado1 =
                partida.atacar(jogador1, new Coordenada(2,3));

        System.out.println(resultado1);

        ResultadoAtaque resultado2 =
                partida.atacar(jogador2, new Coordenada(0,0));

        System.out.println(resultado2);

        ResultadoAtaque resultado3 =
                partida.atacar(jogador1, new Coordenada(3,3));

        System.out.println(resultado3);

        System.out.println(
                partida.verificarVitoria(jogador1)
        );
    }
}