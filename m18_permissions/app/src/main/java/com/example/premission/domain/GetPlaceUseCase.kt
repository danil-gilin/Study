package com.example.premission.domain

import com.example.premission.data.MapRepository
import com.example.premission.entity.place.Feature
import com.example.premission.entity.place.Places
import javax.inject.Inject

class GetPlaceUseCase @Inject constructor(private val mapRepository: MapRepository) {
   suspend fun getPlace(lon_min:Double,lat_min:Double,lon_max:Double,lat_max:Double):List<Feature>  {
      return  mapRepository.getPalce(lon_min,lat_min,lon_max,lat_max)
    }
}