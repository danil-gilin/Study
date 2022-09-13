package com.example.premission.presenter.photo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.premission.domain.AddPhotoBDUseCase
import com.example.premission.entity.photo.NewPhoto
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotoViewModel @Inject constructor (val addPhotoBDUseCase: AddPhotoBDUseCase) : ViewModel() {


    fun addPhoto(photo: NewPhoto) {
        viewModelScope.launch {
            Log.d("Photo",photo.toString())
            addPhotoBDUseCase.addPhoto(photo)
        }
    }

}