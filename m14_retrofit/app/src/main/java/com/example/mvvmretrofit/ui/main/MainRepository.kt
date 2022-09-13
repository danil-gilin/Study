package com.example.mvvmretrofit.ui.main

import com.example.mvvmretrofit.api.main.human.Human
import com.example.mvvmretrofit.api.main.RetrofitServices

class MainRepository {
    suspend fun getRetrofitHuman(): Human {
        val humanList=RetrofitServices.searchHumanApi.getHuman()
        return humanList
    }
}