package com.example.room.ui.main

sealed class State {
    object Success:State()
    object Loading:State()
    data class Error(val error: String):State()
}