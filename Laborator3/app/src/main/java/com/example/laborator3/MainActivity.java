package com.example.laborator3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private int currentIndex = 0;

    private int[] imageIds = {
            R.drawable.bear,
            R.drawable.dog,
            R.drawable.frog,
            R.drawable.elephant
    };

    private String[] descriptions = {
            "Urs",
            "Caine",
            "Broasca",
            "Elefant"
    };

    private Button nextButton, prevButton, detailsButton;

    // Launcher pentru DetailsActivity
    private final ActivityResultLauncher<Intent> detailsLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String newDesc = result.getData().getStringExtra("newDescription");
                    if (newDesc != null) {
                        descriptions[currentIndex] = newDesc;
                        showFragment(); // reafișează fragmentul cu noua descriere
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        detailsButton = findViewById(R.id.detailsButton);

        // Afișăm prima imagine
        showFragment();

        nextButton.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % imageIds.length;
            showFragment();
        });

        prevButton.setOnClickListener(v -> {
            currentIndex = (currentIndex - 1 + imageIds.length) % imageIds.length;
            showFragment();
        });

        detailsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("description", descriptions[currentIndex]);
            detailsLauncher.launch(intent); // lansăm activitatea și așteptăm rezultatul
        });
    }

    private void showFragment() {
        ImageFragment fragment = ImageFragment.newInstance(imageIds[currentIndex], descriptions[currentIndex]);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }
}
