package com.example.mvvmretrofit.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state=_state.asStateFlow()


    fun getHuman(){
        viewModelScope.launch {
            _state.value=State.Loading
            try {
                val human = mainRepository.getRetrofitHuman()
                _state.value=State.Success(human)
            }catch (e:Exception){
                _state.value=State.Error("Ошибка получения данных")
            }
        }
    }
}