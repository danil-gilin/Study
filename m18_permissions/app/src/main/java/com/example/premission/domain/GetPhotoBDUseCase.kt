package com.example.premission.domain

import com.example.premission.data.RepositoryPhoto
import com.example.premission.entity.photo.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotoBDUseCase @Inject constructor(val repositoryPhoto: RepositoryPhoto) {
   fun getPhoto(): Flow<List<Photo>> {
      return  repositoryPhoto.takePhoto()
    }
}