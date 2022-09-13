package com.example.premission.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.premission.entity.photo.Photo

@Database(entities =[Photo::class], version = 1)
abstract class AppDataBase  :RoomDatabase(){
    abstract fun photoDao(): PhotoDao
}