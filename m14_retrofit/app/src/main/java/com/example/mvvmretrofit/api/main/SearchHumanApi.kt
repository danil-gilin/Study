package com.example.mvvmretrofit.api.main


import com.example.mvvmretrofit.api.main.human.Human
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL="https://randomuser.me/"

object RetrofitServices{
    private val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchHumanApi:SearchHumanApi= retrofit.create(SearchHumanApi::class.java)
}

interface SearchHumanApi {
    @GET("api/")
    suspend fun getHuman(): Human
}