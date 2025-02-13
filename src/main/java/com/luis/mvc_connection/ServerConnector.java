package com.luis.mvc_connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector implements Runnable {
    private int port;

    public ServerConnector(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Peer esperando conexiones en el puerto: " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Conexión establecida con: " + socket.getInetAddress().getHostAddress());

                new Thread(new Channel(socket)).start(); // Crear un canal para la comunicación
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor P2P: " + e.getMessage());
        }
    }
}

