package com.cyuan.android.homework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NetworkDataViewModelFactor(private val photoRepository: PhotoRepository) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NetworkDataViewModel(photoRepository) as T
    }
}

