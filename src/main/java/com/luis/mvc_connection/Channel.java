package com.luis.mvc_connection;

import java.io.*;
import java.net.Socket;

public class Channel implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Channel(Socket socket) {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Error en el canal: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Mensaje recibido: " + message);
                out.println("Echo: " + message); // Responder al peer
            }
        } catch (IOException e) {
            System.out.println("Conexión cerrada.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error cerrando el socket: " + e.getMessage());
            }
        }
    }

    public void send(String message) {
        out.println(message);
    }

    public void read(String message) {

    }
}
