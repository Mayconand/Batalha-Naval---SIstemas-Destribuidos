package edu.ucsal.server.remote;

import edu.ucsal.enums.Direcao;
import edu.ucsal.enums.ResultadoAtaque;
import edu.ucsal.enums.TipoBarco;
import edu.ucsal.model.entity.Coordenada;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JogoRemote extends Remote {

    void conectarJogador(String nome)
            throws RemoteException;

    boolean adicionarBarco(
            String jogador,
            Coordenada coordenada,
            TipoBarco tipo,
            Direcao direcao
    ) throws RemoteException;

    ResultadoAtaque atacar(
            String jogador,
            Coordenada coordenada
    ) throws RemoteException;

    String turnoAtual()
            throws RemoteException;

    boolean verificarVitoria(String jogador)
            throws RemoteException;

    boolean partidaPronta()
            throws RemoteException;
}