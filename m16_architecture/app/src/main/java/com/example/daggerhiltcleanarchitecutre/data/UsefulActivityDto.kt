package com.example.daggerhiltcleanarchitecutre.data

import com.example.daggerhiltcleanarchitecutre.entity.UsefulActivity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UsefulActivityDto(
    @Json(name = "accessibility")
    override val accessibility: Double,
    @Json(name = "activity")
    override val activity: String,
    @Json(name = "key")
    override val key: String,
    @Json(name = "link")
    override val link: String?,
    @Json(name = "participants")
    override val participants: Int,
    @Json(name = "price")
    override val price:  Float,
    @Json(name = "type")
    override val type: String
) : UsefulActivity