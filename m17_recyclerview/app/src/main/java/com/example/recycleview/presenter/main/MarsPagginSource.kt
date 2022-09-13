package com.example.recycleview.presenter.main

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.recycleview.data.MarsRepository
import com.example.recycleview.entity.MarsPhoto.Photo

class MarsPagginSource :PagingSource<Int,Photo>() {
   private val repository=MarsRepository()

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
            return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page=params.key ?: 1
        kotlin.runCatching {
            repository.getPagePhotoMars(page)
        }.fold(
            onSuccess = {
                return LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if(it.isEmpty()) null else page+1
                )
            },
            onFailure = { return LoadResult.Error(it)}
        )
    }
}