package com.luis;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Uso: java Main <puerto_Servidor> <IP_PEER>");
            System.exit(1);
        }

        int serverPort = Integer.parseInt(args[0]); // Puerto en el que esta instancia actuará como servidor
        String peerIp = args[1]; // IP del otro peer
        int peerPort = Integer.parseInt(args[2]); // Puerto del otro peer

        new PeerController(serverPort, peerIp, peerPort);
    }
}
