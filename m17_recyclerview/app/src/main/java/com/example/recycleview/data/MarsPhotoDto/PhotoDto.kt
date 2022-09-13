package com.example.recycleview.data.MarsPhotoDto


import com.example.recycleview.entity.MarsPhoto.Photo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoDto(
    @Json(name = "camera")
    override val camera: CameraDto,
    @Json(name = "earth_date")
    override val earthDate: String,
    @Json(name = "id")
    override  val id: Int,
    @Json(name = "img_src")
    override val imgSrc: String,
    @Json(name = "rover")
    override  val rover: RoverDto,
    @Json(name = "sol")
    override val sol: Int
): Photo