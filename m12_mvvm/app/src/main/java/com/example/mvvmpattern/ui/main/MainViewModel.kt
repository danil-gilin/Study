package com.example.mvvmpattern.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    private val _state= MutableStateFlow<State>(State.Success)
    val state=_state.asStateFlow()

    private val _search = Channel<String>()
    val search = _search.receiveAsFlow()

    fun checkSearch(message: String) {
        viewModelScope.launch {
                _state.value = State.Loading
                val rezult = repository.searchData(message)
                _search.send(rezult)
                _state.value = State.Success
        }
    }

    fun checkTextSize(textSize: Int) {
        if(state.value !=State.Loading) {
            if (textSize <= 3) {
                if (textSize == 0) {
                    _state.value = State.Error(null)
                } else {
                    _state.value = State.Error("Короткий текст")
                }
            } else {
                _state.value = State.Success
            }
        }
    }

}