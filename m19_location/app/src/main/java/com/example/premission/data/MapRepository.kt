package com.example.premission.data

import android.util.Log
import com.example.premission.data.api.MapApiService
import com.example.premission.entity.place.Feature
import com.example.premission.entity.place.Places
import javax.inject.Inject

class MapRepository @Inject constructor() {
    suspend fun getPalce(lon_min:Double,lat_min:Double,lon_max:Double,lat_max:Double):List<Feature>  {

        val listPlaces=MapApiService.searchInterestingPlace.getPlace(lon_min,lat_min,lon_max,lat_max)
        return listPlaces.features
    }
}