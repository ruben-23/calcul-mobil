package com.example.servertcp;

import android.app.Service;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.text.format.Formatter;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpServerService extends Service {

    private static final String TAG = "TcpServerService";
    private static final int SERVER_PORT = 8080;
    private boolean isServerRunning = false;
    private ServerSocket serverSocket;
    // Folosim un ExecutorService pentru a rula operatiunile de retea in background
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public IBinder onBind(Intent intent) {
        return null; // Nu este necesara legarea (Bound Service) pentru acest exemplu
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        Log.d("IP", "Phone IP: " + ip);
        if (!isServerRunning) {
            isServerRunning = true;
            Log.d(TAG, "Pornirea Serverului TCP...");
            executor.execute(new ServerTask());
        }
        return START_STICKY; // Serviciul va fi repornit daca este oprit de sistem
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isServerRunning = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "Eroare la inchiderea socket-ului server: " + e.getMessage());
        }
        executor.shutdownNow(); // Opreste firul de executie
        Log.d(TAG, "Serverul TCP oprit.");
    }

    // Task care ruleaza in firul de executie separat (non-UI)
    private class ServerTask implements Runnable {
        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(SERVER_PORT);
                Log.d(TAG, "Serverul Asculta pe portul " + SERVER_PORT);

                while (isServerRunning) {
                    // Asteapta o conexiune
                    Socket clientSocket = serverSocket.accept();
                    Log.d(TAG, "Client Conectat de la: " + clientSocket.getInetAddress());

                    // Gestioneaza comunicarea cu clientul
                    handleClient(clientSocket);
                }
            } catch (Exception e) {
                if (isServerRunning) {
                    Log.e(TAG, "Eroare Server: " + e.getMessage());
                }
            }
        }
    }

    private void handleClient(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            // Trimite mesaj de confirmare catre client
            out.println("SERVER: Conexiune reusita la serverul Android. Trimite un mesaj.");

            // Citeste mesajul de la client
            String clientMessage = in.readLine();
            if (clientMessage != null) {
                Log.d(TAG, "Mesaj primit de la Client: " + clientMessage);
                // Trimite raspuns inapoi la client
                out.println("SERVER: Am primit mesajul tau: " + clientMessage.toUpperCase());
            }

        } catch (Exception e) {
            Log.e(TAG, "Eroare la gestionarea clientului: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                Log.d(TAG, "Conexiune cu clientul inchisa.");
            } catch (Exception e) {
                // Ignorat
            }
        }
    }
}
