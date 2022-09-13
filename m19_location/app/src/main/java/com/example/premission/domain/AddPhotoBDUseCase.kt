package com.example.premission.domain

import com.example.premission.data.RepositoryPhoto
import com.example.premission.entity.photo.NewPhoto
import javax.inject.Inject

class AddPhotoBDUseCase @Inject constructor(private val repositoryPhoto: RepositoryPhoto) {
   suspend fun addPhoto(photo: NewPhoto){
        repositoryPhoto.addPhoto(photo)
    }
}