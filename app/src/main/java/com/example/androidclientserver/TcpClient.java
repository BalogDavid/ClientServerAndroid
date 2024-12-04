package com.example.androidclientserver;

import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("192.168.0.101", 12345)) { // Înlocuiți cu IP-ul dispozitivului Android
            OutputStream output = socket.getOutputStream();
            output.write("Hello from client!\n".getBytes());
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
