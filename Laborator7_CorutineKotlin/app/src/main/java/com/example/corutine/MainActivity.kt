package com.example.corutine

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.TypedValue
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val baseUrl = "https://cti.ubm.ro/cmo/digits/"

    private val imageNames = listOf(
        "img0.jpg", "img1.jpg", "img2.jpg",
        "img3.jpg", "img4.jpg", "img5.jpg",
        "img6.jpg", "img7.jpg", "img8.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val grid = findViewById<GridLayout>(R.id.gridLayout)

        val displayWidth = resources.displayMetrics.widthPixels
        val outerPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            16f,
            resources.displayMetrics
        ).toInt()
        val cellSize = (displayWidth - outerPx) / 3

        // creăm 9 ImageView-uri goale
        val imageViews = mutableListOf<ImageView>()
        repeat(9) {
            val iv = ImageView(this).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = cellSize
                    height = cellSize
                    setMargins(8, 8, 8, 8)
                }
                scaleType = ImageView.ScaleType.CENTER_CROP
                setPadding(4)
            }
            grid.addView(iv)
            imageViews.add(iv)
        }

        // încărcăm imaginile folosind corutine
        imageViews.forEachIndexed { index, iv ->

            lifecycleScope.launch {

                val url = baseUrl + imageNames[index]
                println("Url: $url");

                val bmp = withContext(Dispatchers.IO) {
                    loadBitmapFromUrl(url)
                }

                withContext(Dispatchers.Main) {

                    if (bmp != null)
                        iv.setImageBitmap(bmp)
                    else
                        iv.setImageResource(android.R.drawable.ic_delete)
                }
            }
        }
    }

    private fun loadBitmapFromUrl(urlString: String): Bitmap? {
        return try {
            val url = URL(urlString)
            val conn = url.openConnection() as HttpURLConnection
            conn.connect()
            val stream = conn.inputStream
            BitmapFactory.decodeStream(stream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
