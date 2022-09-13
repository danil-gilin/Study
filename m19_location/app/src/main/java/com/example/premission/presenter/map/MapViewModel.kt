package com.example.premission.presenter.map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.premission.domain.GetPlaceUseCase
import com.example.premission.entity.place.Feature
import com.example.premission.entity.place.Places
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor (private val getPlaceUseCase: GetPlaceUseCase):ViewModel() {

    private val _place= MutableStateFlow<List<Feature>>(emptyList())
    val place =_place.asStateFlow()




    fun getPlace(lon_min:Double,lat_min:Double,lon_max:Double,lat_max:Double){
        viewModelScope.launch {
            _place.value=getPlaceUseCase.getPlace(lon_min,lat_min,lon_max,lat_max)
        }
    }
}