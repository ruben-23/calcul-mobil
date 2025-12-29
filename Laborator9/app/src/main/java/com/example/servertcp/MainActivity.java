package com.example.servertcp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView statusTextView;
    private Button startServerButton;
    private Button stopServerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializarea elementelor UI
        statusTextView = findViewById(R.id.status_text_view);
        startServerButton = findViewById(R.id.start_server_button);
        stopServerButton = findViewById(R.id.stop_server_button);

        // --- Listener Buton Pornire Server ---
        startServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creeaza Intent-ul pentru TcpServerService
                Intent serviceIntent = new Intent(MainActivity.this, TcpServerService.class);

                // Porneste Serviciul
                startService(serviceIntent);

                // Actualizeaza UI-ul
                statusTextView.setText("Server Pornit");
                startServerButton.setEnabled(false);
                stopServerButton.setEnabled(true);
            }
        });

        // --- Listener Buton Oprire Server ---
        stopServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creeaza Intent-ul pentru TcpServerService
                Intent serviceIntent = new Intent(MainActivity.this, TcpServerService.class);

                // Opreste Serviciul
                stopService(serviceIntent);

                // Actualizeaza UI-ul
                statusTextView.setText("Server Oprit");
                startServerButton.setEnabled(true);
                stopServerButton.setEnabled(false);
            }
        });

        // Stare initiala
        stopServerButton.setEnabled(false);
    }
}