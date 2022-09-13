package com.example.premission.entity.photo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Photo")
data class Photo (
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id :Int,
    @ColumnInfo(name = "url_img")
    val url_img :String,
    @ColumnInfo(name = "date")
    val date :String
)