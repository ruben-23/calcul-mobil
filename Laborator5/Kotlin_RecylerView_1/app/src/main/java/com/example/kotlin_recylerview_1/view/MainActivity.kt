package com.example.kotlin_recylerview_1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_recylerview_1.R
import com.example.kotlin_recylerview_1.viewmodel.ProductViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ProductAdapter(this, listOf())
        recyclerView.adapter = adapter

        // Observe LiveData from ViewModel
        viewModel.productList.observe(this, Observer { products ->
            adapter.updateList(products)
        })
    }
}