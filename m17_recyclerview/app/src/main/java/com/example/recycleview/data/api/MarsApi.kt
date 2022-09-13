package com.example.recycleview.data.api

import com.example.recycleview.data.MarsPhotoDto.MarsPhotoDto
import com.example.recycleview.data.MarsPhotoDto.PhotoDto
import com.example.recycleview.entity.MarsPhoto.MarsPhoto
import com.example.recycleview.entity.MarsPhoto.Photo
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL="https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"

object RetrofitServeciesMars{

    private val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val searhPhotoMars= retrofit.create(SearchPhotoMars::class.java)
}


interface SearchPhotoMars{
    @GET("photos?earth_date=2022-1-1&api_key=pEWzl0yBpI0FaqIT2qeHbBbAOF3XaQyj6YhfDW6b")
    suspend fun getmarsPhoto():MarsPhotoDto

    @GET("photos?earth_date=2022-1-1&api_key=pEWzl0yBpI0FaqIT2qeHbBbAOF3XaQyj6YhfDW6b")
    suspend fun  getPageMarsPhoto(@Query("page") page:Int):MarsPhotoDto
}