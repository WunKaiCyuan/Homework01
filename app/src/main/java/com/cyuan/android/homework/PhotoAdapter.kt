package com.cyuan.android.homework

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PhotoAdapter(private val viewModel: NetworkDataViewModel) : RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return if (viewModel.photosLiveData.value.isNullOrEmpty())
            0
        else
            viewModel.photosLiveData.value!!.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val model = viewModel.photosLiveData.value!![position]
        holder.bindData(model)
        holder.itemView.setOnClickListener{
            viewModel.handlePhotoClick(position)
        }
    }

}