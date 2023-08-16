package com.example.recyclerview

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide

class PhotoActivity: ComponentActivity() {
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        imageView = findViewById(R.id.fullPhotoImageView)

        val imageUrl = intent.getStringExtra("imageUrl")
        loadImage(imageUrl)
    }

    private fun loadImage(imageUrl: String?) {
        Glide.with(this)
            .load(imageUrl)
            .into(imageView)
    }

}