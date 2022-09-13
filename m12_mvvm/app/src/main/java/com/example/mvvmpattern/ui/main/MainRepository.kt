package com.example.mvvmpattern.ui.main

import kotlinx.coroutines.delay

class MainRepository{
  private val searchMas= arrayListOf("Computer","Book","Glass")

    suspend fun searchData(item:String):String{
        delay(6000)
        return if(item.lowercase().replaceFirstChar { it.uppercase() } in searchMas){
            "Есть в наличии"
        }else{
            "По запросу "+ item.lowercase().replaceFirstChar { it.uppercase() } +" ничего не найдено"
        }
    }
}