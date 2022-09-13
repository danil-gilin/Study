package com.example.recycleview.entity.MarsPhoto

import com.example.recycleview.data.MarsPhotoDto.CameraDto
import com.example.recycleview.data.MarsPhotoDto.RoverDto

interface Photo {
    val camera: CameraDto
    val earthDate: String
    val id: Int
    val imgSrc: String
    val rover: RoverDto
    val sol: Int
}