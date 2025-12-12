package com.example.lab6_thread;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView labelText;
    private TextView indexText;

    // Lista de imagini (resource ids) si textele asociate
    private final int[] images = {
            R.drawable.img0, R.drawable.img1, R.drawable.img2, R.drawable.img3,
            R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7,
            R.drawable.img8, R.drawable.img9, R.drawable.a, R.drawable.b, R.drawable.joker
    };

    private final String[] descriptions = {
            "Cifra Zero", "Cifra Unu", "Cifra Doi", "Cifra Trei",
            "Cifra Patru", "Cifra Cinci", "Cifra Sase", "Cifra Sapte",
            "Cifra Opt", "Cifra Noua", "Litera A", "Litera B","Joker"
    };

    // Control pentru thread
    private volatile boolean running = false;
    private Thread galleryThread;
    private final long intervalMs = 3000; // 3 secunde intre imagini

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        labelText = findViewById(R.id.labelText);
        indexText = findViewById(R.id.indexText);

        // Initial afisam prima imagine
        if (images.length > 0) {
            imageView.setImageResource(images[0]);
            labelText.setText(descriptions[0]);
            indexText.setText("1 / " + images.length);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startGalleryThread();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopGalleryThread();
    }

    private void startGalleryThread() {
        if (running) return;

        running = true;
        galleryThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                final int total = images.length;

                while (running) {
                    // Avansam indexul
                    index = (index + 1) % total;

                    final int showIndex = index;

                    // Actualizam UI pe firul principal
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageResource(images[showIndex]);
                            labelText.setText(descriptions[showIndex]);
                            indexText.setText((showIndex + 1) + " / " + total);
                        }
                    });

                    // Asteptam intervalul (in thread-ul de fundal)
                    try {
                        Thread.sleep(intervalMs);
                    } catch (InterruptedException e) {
                        // Daca thread-ul este intrerupt, iesim
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        });

        galleryThread.start();
    }

    private void stopGalleryThread() {
        running = false;
        if (galleryThread != null) {
            galleryThread.interrupt();
            galleryThread = null;
        }
    }
}
