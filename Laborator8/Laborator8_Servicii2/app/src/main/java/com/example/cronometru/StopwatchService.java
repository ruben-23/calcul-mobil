package com.example.cronometru;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

public class StopwatchService extends Service {

    private final IBinder binder = new StopwatchBinder();
    private Handler handler = new Handler();
    private long startTime = 0L;
    private long timeInMilliseconds = 0L;
    private boolean isRunning = false;

    // Rulați acest Runnable la fiecare 10 milisecunde pentru a actualiza timpul
    private Runnable runnable = new Runnable() {
        public void run() {
            timeInMilliseconds = System.currentTimeMillis() - startTime;
            // Aici ar trebui să trimiteți timpul la Activity-ul care se conectează (ex. prin Broadcast)
            // Pentru simplitate, îl vom lăsa să ruleze și să fie interogat de Activity.

            if (isRunning) {
                handler.postDelayed(this, 10); // Repetă la fiecare 10ms
            }
        }
    };

    // Clasa Binder pentru a permite Activity-ului să se conecteze
    public class StopwatchBinder extends Binder {
        StopwatchService getService() {
            return StopwatchService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    // Metoda START
    public void startStopwatch() {
        if (!isRunning) {
            startTime = System.currentTimeMillis() - timeInMilliseconds;
            isRunning = true;
            handler.postDelayed(runnable, 0); // Începe imediat
        }
    }

    // Metoda STOP
    public void stopStopwatch() {
        if (isRunning) {
            isRunning = false;
            handler.removeCallbacks(runnable); // Oprește actualizarea
        }
    }

    // Metoda RESET (Opțional)
    public void resetStopwatch() {
        stopStopwatch();
        timeInMilliseconds = 0L;
    }

    // Metodă pentru a obține timpul curent
    public long getTime() {
        if (isRunning) {
            // Recalculează timpul curent înainte de a-l returna, pentru precizie
            return System.currentTimeMillis() - startTime;
        }
        return timeInMilliseconds;
    }
}
