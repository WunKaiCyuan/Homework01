package com.cyuan.android.homework

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_network_data.*
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class NetworkDataActivity : AppCompatActivity() {

    private lateinit var viewModel: NetworkDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_data)

        // bind view model
        val repository = PhotoRepository()
        val factor = NetworkDataViewModelFactor(repository)
        viewModel = ViewModelProvider(this, factor).get(NetworkDataViewModel::class.java)

        rvPhoto.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)
        rvPhoto.adapter = PhotoAdapter(viewModel)

        thread {
            viewModel.loadPhotos()
        }

        viewModel.photosLiveData.observe(this, Observer {
            (rvPhoto.adapter as PhotoAdapter).notifyDataSetChanged()
        })

        viewModel.actionLiveData.observe(this, Observer {
            when (it.mAction) {
                Action.SHOW_DETAIL_ACTIVITY -> {
                    val model = it.mParams[0] as PhotoModel
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("id", model.id)
                    intent.putExtra("title", model.title)
                    intent.putExtra("thumbnailUrl", model.thumbnailUrl)
                    startActivity(intent)
                }
            }
        })
    }
}

