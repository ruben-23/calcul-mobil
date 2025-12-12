package com.example.lab6_asynctask;


import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView labelText;
    private TextView indexText;

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

    private GalleryTask galleryTask;
    private final long intervalMs = 3000; // 3 secunde

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        labelText = findViewById(R.id.labelText);
        indexText = findViewById(R.id.indexText);

        // Pornim AsyncTask-ul
        galleryTask = new GalleryTask();
        galleryTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Oprire task daca activitatea este distrusa
        if (galleryTask != null) {
            galleryTask.cancel(true);
        }
    }

    // -------------------------------
    //   AsyncTask interna
    // -------------------------------
    private class GalleryTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            int total = images.length;
            int index = 0;

            while (!isCancelled()) {
                // Trimitem indexul curent catre UI
                publishProgress(index);

                // Asteptam intervalul specificat
                try {
                    Thread.sleep(intervalMs);
                } catch (InterruptedException e) {
                    break;
                }

                index = (index + 1) % total;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int index = values[0];
            int total = images.length;

            imageView.setImageResource(images[index]);
            labelText.setText(descriptions[index]);
            indexText.setText((index + 1) + " / " + total);
        }
    }
}