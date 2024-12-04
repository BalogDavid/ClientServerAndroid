package com.example.androidclientserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerService extends Service {
    private static final int PORT = 12345;
    private boolean running = true;

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(this::startServer).start();
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Log.d("TCPServer", "Server started on port: " + PORT);
            while (running) {
                Socket client = serverSocket.accept();
                Log.d("TCPServer", "Client connected: " + client.getInetAddress());
                handleClient(client);
            }
        } catch (Exception e) {
            Log.e("TCPServer", "Error in server: ", e);
        }
    }

    private void handleClient(Socket client) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             OutputStream writer = client.getOutputStream()) {
            String message = reader.readLine();
            Log.d("TCPServer", "Received: " + message);
            writer.write(("Echo: " + message + "\n").getBytes());
        } catch (Exception e) {
            Log.e("TCPServer", "Error handling client: ", e);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false;
    }
}