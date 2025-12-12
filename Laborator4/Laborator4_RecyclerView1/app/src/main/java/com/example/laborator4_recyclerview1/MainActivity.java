package com.example.laborator4_recyclerview1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        productList.add(new Product("Laptop", "Procesor i7, 16GB RAM, SSD 512GB", R.drawable.laptop));
        productList.add(new Product("Telefon", "Display 6.5”, 128GB memorie", R.drawable.phone));
        productList.add(new Product("Căști", "Wireless, autonomie 20h", R.drawable.headphones));
        productList.add(new Product("Smartwatch", "Rezistent la apă, monitorizare puls", R.drawable.smrtw));

        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);
    }
}