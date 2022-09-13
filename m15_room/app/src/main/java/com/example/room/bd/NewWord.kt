package com.example.room.bd

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


data class NewWord (
    @PrimaryKey
    @ColumnInfo(name = "word_id")
    val word_id:String?,
    @ColumnInfo(name = "repeat")
    val repeat:Int
)