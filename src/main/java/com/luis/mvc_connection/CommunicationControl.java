package com.luis.mvc_connection;

import com.luis.PeerController;

public class CommunicationControl {
    private ClientConnector clientConnector;
    private ServerConnector serverConnector;
    private volatile Boolean isConnected = false;
    private PeerController peerController;

    public CommunicationControl(int serverPort, String peerIp, int peerPort, PeerController peerController) {
        this.peerController = peerController;
        serverConnector = new ServerConnector(serverPort, this);
        clientConnector = new ClientConnector(peerIp, peerPort, this);
    }

    public void startThreads() {
        new Thread(serverConnector, "Server-Thread").start();
        new Thread(clientConnector, "Client-Thread").start();
    }

    public synchronized void setConnected(boolean connected) {
        this.isConnected = connected;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setActiveChannel(Channel channel) {
        peerController.setActiveChannel(channel);
    }

}

