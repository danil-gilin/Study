package com.example.premission.presenter.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.premission.domain.GetPhotoBDUseCase
import com.example.premission.entity.photo.Photo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getPhotoBDUseCase: GetPhotoBDUseCase) : ViewModel() {
    private val _photo = getPhotoBDUseCase.getPhoto()
    val photo=_photo.asLiveData()



    private val _state= MutableStateFlow<State>(State.Loading)
    val state=_state.asStateFlow()

    init {

    }

    fun getPhoto(){
        viewModelScope.launch {
            _state.value=State.Loading
            try {
                _state.value=State.Success
            }catch (e:Exception){
                _state.value=State.Error("Ошибка загрузки списка из базы данных")
            }
        }
    }

}