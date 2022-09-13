package com.example.recycleview.entity.MarsPhoto

import com.squareup.moshi.Json

interface Rover {
    val id: Int
    val landingDate: String
    val launchDate: String
    val name: String
    val status: String
}