package com.example.laborator4_recyclerview1;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView imageViewDetail;
    TextView textViewName, textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        imageViewDetail = findViewById(R.id.imageViewDetail);
        textViewName = findViewById(R.id.textViewDetailName);
        textViewInfo = findViewById(R.id.textViewDetailInfo);

        // Preluăm datele trimise din intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            String details = extras.getString("details");
            int imageResId = extras.getInt("imageResId");

            textViewName.setText(name);
            textViewInfo.setText(details);
            imageViewDetail.setImageResource(imageResId);
        }
    }
}