package com.example.premission.presenter.main

sealed class State {
    object Success: State()
    object Loading:State()
    data class Error(val message:String):State()
}