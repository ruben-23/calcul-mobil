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

        // Inițializarea elementelor UI
        statusTextView = findViewById(R.id.status_text_view);
        startServerButton = findViewById(R.id.start_server_button);
        stopServerButton = findViewById(R.id.stop_server_button);

        // --- Listener Buton Pornire Server ---
        startServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creează Intent-ul pentru TcpServerService
                Intent serviceIntent = new Intent(MainActivity.this, TcpServerService.class);

                // Pornește Serviciul
                startService(serviceIntent);

                // Actualizează UI-ul
                statusTextView.setText("Server Pornește (Verificați Logcat)");
                startServerButton.setEnabled(false);
                stopServerButton.setEnabled(true);
            }
        });

        // --- Listener Buton Oprire Server ---
        stopServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creează Intent-ul pentru TcpServerService
                Intent serviceIntent = new Intent(MainActivity.this, TcpServerService.class);

                // Oprește Serviciul
                stopService(serviceIntent);

                // Actualizează UI-ul
                statusTextView.setText("Server Oprit");
                startServerButton.setEnabled(true);
                stopServerButton.setEnabled(false);
            }
        });

        // Stare inițială
        stopServerButton.setEnabled(false);
    }

    // În onResume putem verifica starea serviciului, dar pentru simplitate
    // și având în vedere că TcpServerService folosește START_STICKY, ne bazăm pe Logcat.
    // Metoda cea mai robustă ar fi monitorizarea stării prin BroadcastReceiver.
}