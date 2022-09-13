package com.example.premission.presenter.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MapViewModelFactory @Inject constructor(val mapViewModel: MapViewModel) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mapViewModel as T
    }
}