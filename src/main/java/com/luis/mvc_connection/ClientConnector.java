package com.luis.mvc_connection;

import java.io.IOException;
import java.net.Socket;

public class ClientConnector implements Runnable {
    private String peerIp;
    private int port;

    public ClientConnector(String peerIp, int port) {
        this.peerIp = peerIp;
        this.port = port;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = new Socket(peerIp, port);
                System.out.println("Conectado al peer: " + peerIp + " en el puerto " + port);

                new Thread(new Channel(socket)).start(); // Crear un canal para la comunicación
                break; // Salir del loop una vez conectado
            } catch (IOException e) {
                System.out.println("No se pudo conectar con el peer en " + peerIp + ":" + port + ", reintentando...");
            }

            try {
                Thread.sleep(250); // 4 intentos por segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

