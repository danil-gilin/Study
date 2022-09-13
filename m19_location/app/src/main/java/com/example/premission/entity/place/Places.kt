package com.example.premission.entity.place


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Places(
    @Json(name = "features")
    val features: List<Feature>,
    @Json(name = "type")
    val type: String
)