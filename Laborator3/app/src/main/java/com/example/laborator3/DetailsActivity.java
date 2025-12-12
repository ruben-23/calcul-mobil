package com.example.laborator3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class DetailsActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        editText = findViewById(R.id.editTextDescription);
        Button saveButton = findViewById(R.id.saveButton);

        String desc = getIntent().getStringExtra("description");
        if (desc != null) {
            editText.setText(desc);
        }

        saveButton.setOnClickListener(v -> {
            String newDesc = editText.getText().toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("newDescription", newDesc);
            setResult(RESULT_OK, resultIntent);
            finish(); // închide activitatea și revine la MainActivity
        });
    }
}
