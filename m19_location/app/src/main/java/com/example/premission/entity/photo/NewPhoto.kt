package com.example.premission.entity.photo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class NewPhoto (
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id :Int?,
    @ColumnInfo(name = "url_img")
    val url_img :String,
    @ColumnInfo(name = "date")
    val date :String
)