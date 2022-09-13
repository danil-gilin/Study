package com.example.premission.data.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.premission.entity.photo.NewPhoto
import com.example.premission.entity.photo.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
@Query("SELECT * FROM Photo")
 fun takePhoto(): Flow<List<Photo>>
@Insert(entity = Photo::class)
suspend fun addPhoto(photo: NewPhoto)
}


