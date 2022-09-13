package com.example.recycleview.data.MarsPhotoDto


import com.example.recycleview.entity.MarsPhoto.Camera
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CameraDto(
    @Json(name = "full_name")
   override val fullName: String,
    @Json(name = "id")
    override  val id: Int,
    @Json(name = "name")
    override  val name: String,
    @Json(name = "rover_id")
    override  val roverId: Int
):Camera