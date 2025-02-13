package com.luis.mvc_connection;

import java.io.*;
import java.net.Socket;

public class Channel implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Channel(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Mensaje recibido: " + message);
            }
        } catch (IOException e) {
            System.out.println("Error en canal: " + e.getMessage());
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
        System.out.println("mensaje enviado: " + message);
    }
}