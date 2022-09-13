package com.example.daggerhiltcleanarchitecutre.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerhiltcleanarchitecutre.domain.GetUsefulActivityUseCase
import com.example.daggerhiltcleanarchitecutre.entity.UsefulActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor (private val getUsefulActivityUseCase: GetUsefulActivityUseCase) : ViewModel()
{

   private val _state= MutableStateFlow<State>(State.Success(null))
    val state=_state.asStateFlow()

    fun reloadUsefulActivity(){
        viewModelScope.launch {
            _state.value=State.Loading
            try {
                val rezultUsefulActivity = getUsefulActivityUseCase.execute()
                _state.value= State.Success(rezultUsefulActivity)
            }catch (e:Exception){
                _state.value= State.Error("Ошибка обращения к серверу:"+e.toString())
            }
        }
    }
}