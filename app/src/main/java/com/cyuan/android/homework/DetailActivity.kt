package com.cyuan.android.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_detail.*
import kotlin.concurrent.thread

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // bind view model
        val factor = DetailViewModelFactor()
        viewModel = ViewModelProvider(this, factor).get(DetailViewModel::class.java)

        viewModel.idLiveData.observe(this, Observer { tvId.text = it })
        viewModel.titleLiveData.observe(this, Observer { tvTitle.text = it })
        viewModel.thumbnailUrlLiveData.observe(this, Observer {
            thread {
                val picture = NetworkUnit.getPicture(it)
                runOnUiThread {
                    ivCover.setImageBitmap(picture)
                }
            }
        })

        viewModel.bindDetail(
            intent.getStringExtra("id")!!,
            intent.getStringExtra("title")!!,
            intent.getStringExtra("thumbnailUrl")!!
        )
    }
}