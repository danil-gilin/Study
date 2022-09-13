package com.example.mvvmretrofit.ui.main

import com.example.mvvmretrofit.api.main.human.Human

sealed class State{
    data class Success(val human: Human):State()
    object Loading:State()
    data class Error(val error:String):State()
}
