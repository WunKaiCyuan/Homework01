package com.cyuan.android.homework

import android.util.Log
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class PhotoRepository {
    fun getAllPhotos(): List<PhotoModel> {

        val connection =
            URL("https://jsonplaceholder.typicode.com/photos").openConnection() as HttpURLConnection
        connection.doInput = true
        connection.doOutput = false

        connection.requestMethod = "GET"
        connection.connectTimeout = 3000

        if (connection.responseCode == 200) {
            val result = connection.inputStream.bufferedReader(Charsets.UTF_8).readText()
            Log.i(TAG, "getAllPhotos: ${connection.responseCode} $result")

            val list = arrayListOf<PhotoModel>()
            val array = JSONArray(result)
            for (i in 0 until array.length()) {
                val json = array.getJSONObject(i)
                val model = PhotoModel(
                    json.optString("id"),
                    json.optString("title"),
                    json.optString("thumbnailUrl")
                )
                list.add(model)
            }
            return list
        } else {
            Log.i(TAG, "getAllPhotos: ${connection.responseCode}")
            return listOf()
        }
    }

    companion object {
        private const val TAG = "PhotoRepository"
    }
}