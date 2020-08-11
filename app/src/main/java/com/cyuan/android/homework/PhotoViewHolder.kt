package com.cyuan.android.homework

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var model: PhotoModel? = null
    private val handler = Handler()

    private val tvId = itemView.findViewById<TextView>(R.id.tvId)
    private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    private val ivCover = itemView.findViewById<ImageView>(R.id.ivCover)

    fun bindData(photo: PhotoModel) {
        model = photo

        thread {
            handler.post {
                tvId.text = ""
                tvTitle.text = "loading..."
                ivCover.setImageBitmap(null)
            }

            val picture = NetworkUnit.getPicture(photo.thumbnailUrl)
            if (photo == model) {
                handler.post {
                    tvId.text = photo.id
                    tvTitle.text = photo.title
                    ivCover.setImageBitmap(picture)
                }
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): PhotoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo, parent, false)

            return PhotoViewHolder(view)
        }
    }
}