package com.example.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PhotoAdapter(private val photos: List<Photos>,
                   private val onItemClickListener: ItemClickListener2)
    : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_adapter, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        Glide.with(holder.thumbnailImageView)
            .load(photo.thumbnailUrl)
            .into(holder.thumbnailImageView)
        holder.itemView.setOnClickListener{
            onItemClickListener.onItemClick2(photo)
        }

    }

    override fun getItemCount(): Int {
        return photos.size
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val thumbnailImageView: ImageView = itemView.findViewById(R.id.fullPhotoImageView)
    }
}