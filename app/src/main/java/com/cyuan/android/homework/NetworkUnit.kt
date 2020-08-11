package com.cyuan.android.homework

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.HttpURLConnection
import java.net.URL

class NetworkUnit {
    companion object {
        fun getPicture(url: String): Bitmap? {
            val coverUrl = URL(url)
            val connection = coverUrl.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.doInput = true
            connection.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36"
            )
            val stream = connection.inputStream
            return BitmapFactory.decodeStream(stream)
        }
    }
}