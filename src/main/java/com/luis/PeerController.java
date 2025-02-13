package com.luis;

import com.luis.mvc_connection.CommunicationControl;

public class PeerController {
    private CommunicationControl communicationControl;

    public PeerController(int serverPort, String peerIp, int peerPort) {
        communicationControl = new CommunicationControl(serverPort, peerIp, peerPort);
        communicationControl.start();
    }

    public void recievedBall(){

    }

    public void sendBall(){

    }
}
