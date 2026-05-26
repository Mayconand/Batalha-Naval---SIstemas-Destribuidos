package edu.ucsal.client;

import edu.ucsal.enums.*;
import edu.ucsal.model.entity.Coordenada;
import edu.ucsal.server.remote.JogoRemote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {

    public static void main(String[] args) {

        try {

            Registry registry =
                    LocateRegistry.getRegistry(
                            "192.168.0.74", //TROCAR PELO IP REAL DO SERVIDOR
                            1099
                    );

            JogoRemote jogo =
                    (JogoRemote) registry.lookup(
                            "BatalhaNaval"
                    );

            jogo.conectarJogador("Maycon");

            jogo.adicionarBarco(
                    "Maycon",
                    new Coordenada(2,3),
                    TipoBarco.SUBMARINO,
                    Direcao.HORIZONTAL
            );

            ResultadoAtaque resultado =
                    jogo.atacar(
                            "Maycon",
                            new Coordenada(1,1)
                    );

            System.out.println(resultado);

            System.out.println(
                    jogo.turnoAtual()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}