package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : ComponentActivity(), ItemClickListener {

    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var albumRecyclerView: RecyclerView
    private lateinit var albums: List<Album>
    private lateinit var getAlbumAPI: GetAlbumAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        albumRecyclerView = findViewById(R.id.albumRecyclerView)
        albumRecyclerView.layoutManager = GridLayoutManager(this, 2)
        getAlbumAPI = com.example.recyclerview.Retrofit.getInstance().create(GetAlbumAPI::class.java)
        fetchAlbums()


    }

    private fun fetchAlbums() {
        val call = getAlbumAPI.getAlbumData()

        call.enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if (response.isSuccessful) {
                    albums = response.body() ?: emptyList()
                    showAlbums(albums)
                } else {
                    Toast.makeText(this@MainActivity, "Failed to fetch albums", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to fetch albums", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showAlbums(albums: List<Album>) {
        albumAdapter = AlbumAdapter(albums, this)
        /*{ album ->
            val intent = Intent(this@MainActivity, PhotoActivity::class.java)
            intent.putExtra("albumId", album.id)
            startActivity(intent)
        }*/
        albumRecyclerView.adapter = albumAdapter
    }

    override fun onItemClick(position: Int, id: String) {

        val intent = Intent(this@MainActivity, PhotoListActivity::class.java)
        intent.putExtra("albumId", id)
        startActivity(intent)
    }


}

