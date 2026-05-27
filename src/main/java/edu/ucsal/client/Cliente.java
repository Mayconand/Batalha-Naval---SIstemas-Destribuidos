package edu.ucsal.client;

import edu.ucsal.enums.*;
import edu.ucsal.model.entity.Coordenada;
import edu.ucsal.server.remote.JogoRemote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        try {

            Registry registry =
                    LocateRegistry.getRegistry(
                            "10.8.184.7", //TROCAR PELO IP REAL DO SERVIDOR
                            1099
                    );

            JogoRemote jogo =
                    (JogoRemote) registry.lookup(
                            "BatalhaNaval"
                    );

            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine();

            jogo.conectarJogador(nome);

            System.out.println("Conectado!");

            while(!jogo.partidaPronta()){

                System.out.println(
                        "Aguardando outro jogador..."
                );

                Thread.sleep(1000);
            }

            System.out.println("Partida iniciada!");

            for (TipoBarco tipo : TipoBarco.values()) {

                int quantidade = tipo.getQuantidadeMaxima();

                for (int i = 0; i < quantidade; i++) {

                    boolean adicionado = false;

                    while (!adicionado) {

                        System.out.println(
                                "\nPosicionando " + tipo +
                                        " (" + (i + 1) + "/" + quantidade + ")"
                        );

                        System.out.print("X inicial: ");
                        int x = scanner.nextInt();

                        System.out.print("Y inicial: ");
                        int y = scanner.nextInt();

                        System.out.print("Direção (H/V): ");
                        String dir = scanner.next().toUpperCase();

                        Direcao direcao =
                                dir.equals("H")
                                        ? Direcao.HORIZONTAL
                                        : Direcao.VERTICAL;

                        adicionado = jogo.adicionarBarco(
                                nome,
                                new Coordenada(x, y),
                                tipo,
                                direcao
                        );

                        if (adicionado) {

                            System.out.println(
                                    "Barco adicionado com sucesso!"
                            );

                            System.out.println(
                                    jogo.visualizarMeuTabuleiro(nome)
                            );

                        } else {

                            System.out.println(
                                    "Posição inválida! Tente novamente."
                            );
                        }
                    }
                }
            }

            jogo.jogadorPronto(nome);

            System.out.println(
                    "Aguardando adversário posicionar os barcos..."
            );

            while(!jogo.todosProntos()){

                Thread.sleep(1000);
            }

            while (!jogo.verificarVitoria(nome)) {

                if (jogo.turnoAtual().equals(nome)) {

                    System.out.println("\n=== SEU TURNO ===");

                    System.out.print("X: ");
                    int x = scanner.nextInt();

                    System.out.print("Y: ");
                    int y = scanner.nextInt();

                    ResultadoAtaque resultado =
                            jogo.atacar(
                                    nome,
                                    new Coordenada(x, y)
                            );

                    System.out.println(
                            "Resultado: " + resultado
                    );

                    System.out.println(
                            "\n=== MEUS ATAQUES ==="
                    );

                    System.out.println(
                            jogo.visualizarAtaques(nome)
                    );

                    System.out.println(
                            "\n=== MEU TABULEIRO ==="
                    );

                    System.out.println(
                            jogo.visualizarMeuTabuleiro(nome)
                    );

                    if (jogo.verificarVitoria(nome)) {

                        System.out.println(
                                "VOCÊ VENCEU!"
                        );

                        break;
                    }

                } else {

                    System.out.println(
                            "Aguardando turno do adversário..."
                    );

                    Thread.sleep(1000);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}