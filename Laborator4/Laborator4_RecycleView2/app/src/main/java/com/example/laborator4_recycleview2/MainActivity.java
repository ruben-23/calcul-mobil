package com.example.laborator4_recycleview2;


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
        productList.add(new Product("Laptop Lenovo", "3.500 RON", R.drawable.laptop));
        productList.add(new Product("Telefon Samsung", "2.400 RON", R.drawable.phone));
        productList.add(new Product("Căști Bluetooth", "350 RON", R.drawable.headphones));
        productList.add(new Product("Smartwatch Huawei", "899 RON", R.drawable.smrtw));

        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);
    }
}