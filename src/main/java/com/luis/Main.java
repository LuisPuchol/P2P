package com.luis;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PeerController peer = new PeerController();

        Scanner scanner = new Scanner(System.in);

        // Loop para enviar mensajes
        while (true) {
            String message = scanner.nextLine();
            if (message.equals("exit")) {
                break;
            }
            peer.sendMessage(message);
        }
    }
}
