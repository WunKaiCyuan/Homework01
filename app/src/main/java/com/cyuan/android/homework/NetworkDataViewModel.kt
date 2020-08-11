package com.cyuan.android.homework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NetworkDataViewModel(private val photoRepository: PhotoRepository) : ViewModel() {

    val photosLiveData = MutableLiveData<List<PhotoModel>>()
    val actionLiveData = MutableLiveData<Action>()

    fun loadPhotos() {
        photosLiveData.postValue(photoRepository.getAllPhotos())
    }

    fun handlePhotoClick(position: Int) {
        val model = photosLiveData.value!![position]
        actionLiveData.value = Action(Action.SHOW_DETAIL_ACTIVITY, listOf(model))
    }
}

