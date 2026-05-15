package edu.ucsal.server.remote;

import java.rmi.Remote;

public interface JogoRemote extends Remote {

    int conectarJogador(String nome);

    int posicionarBarco(int x, int y, );

    int disparar(int x, int y);
}
