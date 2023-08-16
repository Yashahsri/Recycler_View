package com.example.recyclerview
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.Album
import com.example.recyclerview.R

class AlbumAdapter(
    private val albums: List<Album>,
    private val onItemClickListener: ItemClickListener)
 : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_adapter, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        holder.bind(album)
        holder.itemView.setOnClickListener{
            onItemClickListener.onItemClick(position, album.id.toString())
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)


        fun bind(album: Album) {
            titleTextView.text = album.title
        }
    }
}