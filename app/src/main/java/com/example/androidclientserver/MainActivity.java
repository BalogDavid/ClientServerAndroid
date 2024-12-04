package com.example.androidclientserver;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean isServerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button startServerBtn = findViewById(R.id.startServerBtn);
        Button sendMessageBtn = findViewById(R.id.sendMessageBtn);
        EditText serverIpEditText = findViewById(R.id.serverIpEditText);
        EditText messageEditText = findViewById(R.id.messageEditText);

        // Start Server Button
        startServerBtn.setOnClickListener(view -> {
            if (!isServerRunning) {
                Intent intent = new Intent(this, TcpServerService.class);
                startService(intent);
                Toast.makeText(this, "Server started!", Toast.LENGTH_SHORT).show();
                isServerRunning = true;
            } else {
                Toast.makeText(this, "Server already running!", Toast.LENGTH_SHORT).show();
            }
        });

        // Send Message Button
        sendMessageBtn.setOnClickListener(view -> {
            String serverIp = serverIpEditText.getText().toString().trim();
            String message = messageEditText.getText().toString();

            if (!serverIp.isEmpty() && !message.isEmpty()) {
                new TcpClientTask().execute(serverIp, message);
            } else {
                Toast.makeText(this, "Enter server IP and message!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}