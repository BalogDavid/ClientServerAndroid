package com.example.androidclientserver;

import android.os.AsyncTask;
import android.util.Log;

import java.io.OutputStream;
import java.net.Socket;

public class TcpClientTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... params) {
        try (Socket socket = new Socket(params[0], 12345)) { // params[0] = IP server
            OutputStream output = socket.getOutputStream();
            String message = params[1]; // params[1] = mesaj
            output.write((message + "\n").getBytes());
            output.flush();
            Log.d("TCPClient", "Message sent: " + message);
        } catch (Exception e) {
            Log.e("TCPClient", "Error: ", e);
        }
        return null;
    }
}