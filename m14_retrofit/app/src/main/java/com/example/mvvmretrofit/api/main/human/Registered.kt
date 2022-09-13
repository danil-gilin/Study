package com.example.mvvmretrofit.api.main.human


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Registered(
    @Json(name = "age")
    val age: Int,
    @Json(name = "date")
    val date: String
)