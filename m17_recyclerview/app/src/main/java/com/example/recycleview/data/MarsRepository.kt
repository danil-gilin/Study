package com.example.recycleview.data

import android.util.Log
import com.example.recycleview.data.api.RetrofitServeciesMars
import com.example.recycleview.entity.MarsPhoto.MarsPhoto
import com.example.recycleview.entity.MarsPhoto.Photo
import kotlinx.coroutines.delay
import javax.inject.Inject

class MarsRepository @Inject constructor() {

   suspend fun getListMarsPhoto():MarsPhoto{
      return RetrofitServeciesMars.searhPhotoMars.getmarsPhoto()
   }

   suspend fun getPagePhotoMars(page:Int):List<Photo>{
       delay(3000)
       return RetrofitServeciesMars.searhPhotoMars.getPageMarsPhoto(page).photos
   }
}