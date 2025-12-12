package com.example.kotlin_recylerview_1.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_recylerview_1.R
import com.example.kotlin_recylerview_1.model.Product


class ProductAdapter(
    private val context: Context,
    private var productList: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val textName: TextView = itemView.findViewById(R.id.textViewName)
        val textDetails: TextView = itemView.findViewById(R.id.textViewDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        holder.imageProduct.setImageResource(product.imageResId)
        holder.textName.text = product.name
        holder.textDetails.text = product.details

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java).apply {
                putExtra("name", product.name)
                putExtra("details", product.details)
                putExtra("imageResId", product.imageResId)
            }
            context.startActivity(intent)
        }
    }

    fun updateList(newList: List<Product>) {
        productList = newList
        notifyDataSetChanged()
    }
}