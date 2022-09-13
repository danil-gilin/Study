package com.example.premission.presenter.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class PhotoViewModelFactory@Inject constructor(val photoViewModel: PhotoViewModel) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return photoViewModel as T
    }
}