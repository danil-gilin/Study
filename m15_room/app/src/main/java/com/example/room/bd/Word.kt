package com.example.room.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word(
    @PrimaryKey
    @ColumnInfo(name = "word_id")
    val word_id:String,
    @ColumnInfo(name = "repeat")
    val repeat:Int=1
)