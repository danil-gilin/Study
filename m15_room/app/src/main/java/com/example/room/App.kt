package com.example.room

import android.app.Application
import androidx.room.Room
import com.example.room.bd.AppDataBase

class App:Application() {

    lateinit var db:AppDataBase

    override fun onCreate() {
        super.onCreate()
        db= Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "db"
        ).build()
    }
}