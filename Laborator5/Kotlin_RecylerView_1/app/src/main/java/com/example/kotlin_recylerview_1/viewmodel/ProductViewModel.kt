package com.example.kotlin_recylerview_1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_recylerview_1.R
import com.example.kotlin_recylerview_1.model.Product

class ProductViewModel : ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    init {
        loadProducts()
    }

    private fun loadProducts() {
        val products = listOf(
            Product("Laptop Lenovo", "Procesor i7, 16GB RAM, SSD 512GB", R.drawable.laptop),
            Product("Telefon Samsung", "Display 6.5”, 128GB memorie", R.drawable.phone),
            Product("Căști Bluetooth", "Wireless, autonomie 20h", R.drawable.headphones),
            Product("Smartwatch Huawei", "Rezistent la apă, monitorizare puls", R.drawable.smrtw)
        )
        _productList.value = products
    }
}