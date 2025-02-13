package com.luis.mvc_connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector implements Runnable {
    private int port;
    private CommunicationControl control;


    public ServerConnector(int port, CommunicationControl control) {
        this.port = port;
        this.control = control;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Escuchando en puerto: " + port);

            while (!control.isConnected()) {
                Socket socket = serverSocket.accept();
                System.out.println("Conexión aceptada de: " + socket.getInetAddress().getHostAddress());
                control.setConnected(true);
                Channel channel = new Channel(socket);
                control.setActiveChannel(channel);
                new Thread(channel).start();
            }
        } catch (IOException e) {
            System.out.println("Error en servidor: " + e.getMessage());
        }
    }
}

