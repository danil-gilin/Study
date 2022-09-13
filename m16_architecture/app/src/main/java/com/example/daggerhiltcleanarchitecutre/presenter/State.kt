package com.example.daggerhiltcleanarchitecutre.presenter

import com.example.daggerhiltcleanarchitecutre.entity.UsefulActivity

sealed class State{
    data class  Success(val usefulActivity: UsefulActivity?):State()
    object Loading:State()
    data class Error(val message: String):State()
}
