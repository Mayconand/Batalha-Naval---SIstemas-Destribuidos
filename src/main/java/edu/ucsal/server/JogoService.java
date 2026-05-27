package edu.ucsal.server;

import edu.ucsal.enums.Direcao;
import edu.ucsal.enums.ResultadoAtaque;
import edu.ucsal.enums.TipoBarco;
import edu.ucsal.model.entity.*;
import edu.ucsal.server.remote.JogoRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class JogoService extends UnicastRemoteObject
        implements JogoRemote {

    private Jogador jogador1;
    private Jogador jogador2;

    private Partida partida;

    public JogoService() throws RemoteException {
        super();
    }

    @Override
    public void conectarJogador(String nome)
            throws RemoteException {

        if(jogador1 == null){

            jogador1 = new Jogador(nome);

            System.out.println(nome + " conectou");

            return;
        }

        if(jogador2 == null){

            jogador2 = new Jogador(nome);

            System.out.println(nome + " conectou");

            partida = new Partida(jogador1, jogador2);

            System.out.println("Partida iniciada");

            return;
        }

        System.out.println("Sala cheia");
    }

    private Jogador buscarJogador(String nome){

        if(jogador1 != null &&
                jogador1.getNome().equals(nome)){

            return jogador1;
        }

        if(jogador2 != null &&
                jogador2.getNome().equals(nome)){

            return jogador2;
        }

        return null;
    }

    @Override
    public boolean adicionarBarco(
            String jogadorNome,
            Coordenada coordenada,
            TipoBarco tipo,
            Direcao direcao
    ) throws RemoteException {

        Jogador jogador = buscarJogador(jogadorNome);

        if(jogador == null){
            return false;
        }

        Barco barco =
                new Barco(coordenada, tipo, direcao);

        return jogador.getTabuleiro()
                .adicionarBarco(barco);
    }

    @Override
    public ResultadoAtaque atacar(
            String jogadorNome,
            Coordenada coordenada

    ) throws RemoteException {

        Jogador jogador =
                buscarJogador(jogadorNome);

        if(jogador == null){
            System.out.println("Partida: " + partida);
            System.out.println("Jogador: " + jogador);
            System.out.println("Coordenada: " + coordenada);
            return ResultadoAtaque.FORA_DO_TURNO;
        }
        System.out.println("Partida: " + partida);
        System.out.println("Jogador: " + jogador);
        System.out.println("Coordenada: " + coordenada);
        return partida.atacar(jogador, coordenada);
    }

    @Override
    public String turnoAtual()
            throws RemoteException {

        if(partida == null){
            return "Sem partida";
        }

        return partida.getTurnoAtual()
                .getNome();
    }

    @Override
    public boolean verificarVitoria(String jogadorNome)
            throws RemoteException {

        Jogador jogador =
                buscarJogador(jogadorNome);

        if(jogador == null){
            return false;
        }

        return partida.verificarVitoria(jogador);
    }

    @Override
    public boolean partidaPronta()
            throws RemoteException {

        return partida != null;
    }

    @Override
    public String visualizarMeuTabuleiro(String jogadorNome)
            throws RemoteException {

        Jogador jogador = buscarJogador(jogadorNome);

        if(jogador == null){
            return "Jogador não encontrado";
        }

        return jogador.getTabuleiro()
                .visualizarTabuleiro();
    }

    @Override
    public String visualizarAtaques(String jogadorNome)
            throws RemoteException {

        Jogador jogador = buscarJogador(jogadorNome);

        if(jogador == null){
            return "Jogador não encontrado";
        }

        return jogador.visualizarAtaques();
    }
}