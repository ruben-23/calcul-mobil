package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPlay, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);

        Intent serviceIntent = new Intent(this, MusicService.class);

        btnPlay.setOnClickListener(v -> {
            startService(serviceIntent);
        });

        btnStop.setOnClickListener(v -> {
            stopService(serviceIntent);
        });
    }
}
