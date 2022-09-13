package com.example.mvvmretrofit.api.main.human


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Human(
    @Json(name = "info")
    val info: Info,
    @Json(name = "results")
    val results: List<Result>
)