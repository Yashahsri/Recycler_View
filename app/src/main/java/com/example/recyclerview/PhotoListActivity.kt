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

class PhotoListActivity: ComponentActivity(),ItemClickListener2 {
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var getPhotosAPI: GetPhotosAPI
    private lateinit var photoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val albumId = intent.getStringExtra("albumId")
        photoRecyclerView = findViewById(R.id.photoRecyclerView)
        photoRecyclerView.layoutManager = GridLayoutManager(this, 3)
        getPhotosAPI = com.example.recyclerview.Retrofit.getInstance().create(GetPhotosAPI::class.java)
        Toast.makeText(this,albumId.toString(),Toast.LENGTH_LONG).show()

        fetchPhotos(albumId)
    }

    private fun fetchPhotos(albumId: String?) {
        albumId?.let {
            it
            val call = getPhotosAPI.getPhotosData(it)

            call.enqueue(object : Callback<List<Photos>> {
                override fun onResponse(
                    call: Call<List<Photos>>,
                    response: Response<List<Photos>>
                ) =
                    if (response.isSuccessful) {
                        val photos = response.body() ?: emptyList()
                        setPhotos(photos)
                    } else {
                        Toast.makeText(
                            this@PhotoListActivity,
                            "Failed to fetch photos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
                    Toast.makeText(
                        this@PhotoListActivity,
                        "Failed to fetch photos",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        }

    }

    private fun setPhotos(photos: List<Photos>) {

        photoAdapter = PhotoAdapter(photos, this@PhotoListActivity)

        photoRecyclerView.adapter = photoAdapter
    }

    override fun onItemClick2(photo: Photos) {
        val intent = Intent(this@PhotoListActivity, PhotoActivity::class.java)
        intent.putExtra("imageUrl", photo.url)
        startActivity(intent)
    }


}