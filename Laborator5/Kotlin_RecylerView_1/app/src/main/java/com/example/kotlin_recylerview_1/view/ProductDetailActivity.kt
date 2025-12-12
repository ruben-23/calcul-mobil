package com.example.kotlin_recylerview_1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlin_recylerview_1.R

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val imageView: ImageView = findViewById(R.id.imageViewDetail)
        val textName: TextView = findViewById(R.id.textViewDetailName)
        val textDetails: TextView = findViewById(R.id.textViewDetailInfo)

        val name = intent.getStringExtra("name")
        val details = intent.getStringExtra("details")
        val imageResId = intent.getIntExtra("imageResId", 0)

        textName.text = name
        textDetails.text = details
        imageView.setImageResource(imageResId)
    }
}
