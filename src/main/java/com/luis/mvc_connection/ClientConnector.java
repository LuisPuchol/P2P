package com.luis.mvc_connection;

import java.io.IOException;
import java.net.Socket;

public class ClientConnector implements Runnable {
    private String peerIp;
    private int port;
    private CommunicationControl control;

    public ClientConnector(String peerIp, int port, CommunicationControl control) {
        this.peerIp = peerIp;
        this.port = port;
        this.control = control;
    }

    @Override
    public void run() {
        while (!control.isConnected()) {
            try {
                Socket socket = new Socket(peerIp, port);
                control.setConnected(true);
                System.out.println("Conectado al peer en puerto " + port);
                Channel channel = new Channel(socket);
                control.setActiveChannel(channel);
                new Thread(channel).start();
                break;
            } catch (IOException e) {
                System.out.println("Esperando peer en puerto " + port);
            }

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}