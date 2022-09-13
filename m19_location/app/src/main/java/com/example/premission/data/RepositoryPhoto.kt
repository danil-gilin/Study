package com.example.premission.data

import com.example.premission.data.bd.PhotoDao
import com.example.premission.entity.photo.NewPhoto
import com.example.premission.entity.photo.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryPhoto@Inject constructor(private val photoDao: PhotoDao) {
     fun takePhoto(): Flow<List<Photo>> {
     return photoDao.takePhoto()
    }
    suspend fun addPhoto(photo: NewPhoto){
        photoDao.addPhoto(photo)
    }
}