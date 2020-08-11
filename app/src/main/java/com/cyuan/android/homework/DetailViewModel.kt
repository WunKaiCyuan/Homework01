package com.cyuan.android.homework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel() : ViewModel() {

    val idLiveData = MutableLiveData<String>()
    val titleLiveData = MutableLiveData<String>()
    val thumbnailUrlLiveData = MutableLiveData<String>()

    fun bindDetail(id: String, title: String, thumbnailUrl: String) {
        idLiveData.value = id
        titleLiveData.value = title
        thumbnailUrlLiveData.value = thumbnailUrl
    }
}