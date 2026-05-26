package edu.ucsal.server;

import edu.ucsal.server.remote.JogoRemote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

    public static void main(String[] args) {

        try {

            JogoRemote service =
                    new JogoService();

            Registry registry =
                    LocateRegistry.createRegistry(1099);

            registry.rebind(
                    "BatalhaNaval",
                    service
            );

            System.out.println(
                    "Servidor RMI iniciado"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}