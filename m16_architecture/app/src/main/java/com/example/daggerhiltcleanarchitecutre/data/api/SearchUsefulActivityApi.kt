package com.example.daggerhiltcleanarchitecutre.data.api

import com.example.daggerhiltcleanarchitecutre.data.UsefulActivityDto
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL="http://www.boredapi.com/api/"

object RetrofitServices{
    private val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchUsefulActivity= retrofit.create(SearchUsefulActivity::class.java)
}

interface SearchUsefulActivity{
    @GET("activity")
    suspend fun getUsefulActiviti():UsefulActivityDto
}