package com.luis;

import com.luis.mvc_connection.Channel;
import com.luis.mvc_connection.CommunicationControl;

import java.io.IOException;
import java.net.ServerSocket;

public class PeerController {
    private CommunicationControl communicationControl;
    private static final int PRIMARY_PORT = 100;
    private static final int SECONDARY_PORT = 101;
    private Channel activeChannel;


    public PeerController() {
        initializePeer();
    }

    private void initializePeer() {
        //prueba 1 con el 100
        if (isPortAvailable(PRIMARY_PORT)) {
            System.out.println("Iniciando como peer primario en puerto " + PRIMARY_PORT);
            communicationControl = new CommunicationControl(PRIMARY_PORT, "localhost", SECONDARY_PORT, this);
        }
        //prueba 2 con el 101
        else {
            System.out.println("Iniciando como peer secundario en puerto " + SECONDARY_PORT);
            communicationControl = new CommunicationControl(SECONDARY_PORT, "localhost", PRIMARY_PORT, this);
        }
        communicationControl.startThreads();
    }

    private boolean isPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void recievedBall(){

    }

    public void sendBall(){

    }

    public void setActiveChannel(Channel channel) {
        this.activeChannel = channel;
    }

    public void sendMessage(String message) {
        if (activeChannel != null) {
            activeChannel.sendMessage(message);
        }
    }
}
