package com.example.cronometru;

import androidx.appcompat.app.AppCompatActivity;



import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private StopwatchService stopwatchService;
    private boolean isBound = false;
    private TextView timeTextView;
    private Button startStopButton;
    private Handler handler = new Handler();

    // Runnable pentru actualizarea UI-ului
    private final Runnable updateTimeRunnable = new Runnable() {
        @Override
        public void run() {
            if (isBound) {
                long time = stopwatchService.getTime();
                updateTimeText(time);
                handler.postDelayed(this, 50); // Actualizează UI-ul la fiecare 50ms
            }
        }
    };

    // Definiția ServiceConnection pentru a gestiona legarea
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StopwatchService.StopwatchBinder binder = (StopwatchService.StopwatchBinder) service;
            stopwatchService = binder.getService();
            isBound = true;
            // Începe actualizarea UI-ului când serviciul este conectat
            handler.post(updateTimeRunnable);
            // Puteți actualiza textul butonului în funcție de starea serviciului
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            stopwatchService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Presupunând că aveți un layout

        timeTextView = findViewById(R.id.time_text_view); // TextView pentru timp
        startStopButton = findViewById(R.id.start_stop_button); // Buton Start/Stop

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBound) {
                    // Logica pentru Start/Stop
                    if (startStopButton.getText().equals("Start")) {
                        stopwatchService.startStopwatch();
                        startStopButton.setText("Stop");
                    } else {
                        stopwatchService.stopStopwatch();
                        startStopButton.setText("Start");
                    }
                }
            }
        });

        // Opțional: Adăugați un buton pentru Reset
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Leagă serviciul când Activity-ul devine vizibil
        Intent intent = new Intent(this, StopwatchService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Dezleagă serviciul când Activity-ul nu mai este vizibil
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
        handler.removeCallbacks(updateTimeRunnable);
    }

    // Metodă utilitară pentru a formata milisecundele în H:MM:SS:ms
    private void updateTimeText(long time) {
        int hours = (int) (time / 3600000);
        int minutes = (int) (time % 3600000) / 60000;
        int seconds = (int) ((time % 60000) / 1000);
        int milliseconds = (int) (time % 1000) / 10; // Afișăm doar zecimile de secundă

        String timeFormatted = String.format(Locale.getDefault(),
                "%02d:%02d:%02d.%02d", hours, minutes, seconds, milliseconds);
        timeTextView.setText(timeFormatted);
    }
}