package com.example.recycleview.data.MarsPhotoDto


import com.example.recycleview.entity.MarsPhoto.Rover
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoverDto(
    @Json(name = "id")
    override val id: Int,
    @Json(name = "landing_date")
    override val landingDate: String,
    @Json(name = "launch_date")
    override val launchDate: String,
    @Json(name = "name")
    override val name: String,
    @Json(name = "status")
    override val status: String
):Rover