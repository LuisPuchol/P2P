package com.luis.mvc_connection;

public class CommunicationControl {
    private ClientConnector clientConnector;
    private ServerConnector serverConnector;

    public CommunicationControl(int serverPort, String peerIp, int peerPort) {
        serverConnector = new ServerConnector(serverPort); // Servidor escucha en el puerto propio
        clientConnector = new ClientConnector(peerIp, peerPort); // Cliente se conecta al otro peer
    }

    public void start() {
        new Thread(serverConnector).start();
        new Thread(clientConnector).start();
    }
}

