package com.example.daggerhiltcleanarchitecutre.data

import com.example.daggerhiltcleanarchitecutre.data.api.RetrofitServices
import com.example.daggerhiltcleanarchitecutre.entity.UsefulActivity
import javax.inject.Inject

class UsefulActivityRepository @Inject constructor() {

    suspend fun getUsefulActivity():UsefulActivity{
        return RetrofitServices.searchUsefulActivity.getUsefulActiviti()
    }
}