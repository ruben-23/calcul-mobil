package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemList: ArrayList<Item>
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setăm layout-ul activității
        setContentView(R.layout.activity_main)

        // Inițializăm RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        // Inițializăm lista de date
        itemList = ArrayList()
        for (i in 1..50) {
            itemList.add(Item("Element $i"))
        }

        // Setăm adapterul
        itemAdapter = ItemAdapter(itemList)
        recyclerView.adapter = itemAdapter
    }
}
