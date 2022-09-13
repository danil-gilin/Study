package com.example.recycleview.data.MarsPhotoDto


import com.example.recycleview.entity.MarsPhoto.MarsPhoto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarsPhotoDto(
    @Json(name = "photos")
  override  val photos: List<PhotoDto>
):MarsPhoto