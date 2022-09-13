package com.example.premission.data.api

import com.example.premission.entity.place.Places
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL="http://api.opentripmap.com/0.1/ru/places/"

object MapApiService {
    private val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()


    val searchInterestingPlace:SearchInterestingPlaceApi= retrofit.create(SearchInterestingPlaceApi::class.java)
}
interface SearchInterestingPlaceApi{
    @GET("bbox?format=geojson&apikey=5ae2e3f221c38a28845f05b679fc730b4b7a8f029fe15d81f2d995eb")
    suspend fun getPlace(@Query("lon_min") lon_min:Double,
                         @Query("lat_min") lat_min:Double,
                         @Query("lon_max")  lon_max:Double,
                         @Query("lat_max")  lat_max:Double):Places
}
