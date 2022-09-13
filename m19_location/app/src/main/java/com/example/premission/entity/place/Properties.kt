package com.example.premission.entity.place


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Properties(
    @Json(name = "kinds")
    val kinds: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "osm")
    val osm: String?,
    @Json(name = "rate")
    val rate: Int,
    @Json(name = "wikidata")
    val wikidata: String?,
    @Json(name = "xid")
    val xid: String
)