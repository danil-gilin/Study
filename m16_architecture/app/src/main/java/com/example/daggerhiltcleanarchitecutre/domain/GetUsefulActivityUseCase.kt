package com.example.daggerhiltcleanarchitecutre.domain

import com.example.daggerhiltcleanarchitecutre.data.UsefulActivityRepository
import com.example.daggerhiltcleanarchitecutre.entity.UsefulActivity
import javax.inject.Inject

class GetUsefulActivityUseCase @Inject constructor(private val usefulActivityRepository: UsefulActivityRepository) {
    suspend fun execute():UsefulActivity{
       return usefulActivityRepository.getUsefulActivity()
    }
}