package com.example.recycleview.presenter.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.recycleview.data.MarsRepository
import com.example.recycleview.entity.MarsPhoto.MarsPhoto
import com.example.recycleview.entity.MarsPhoto.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val marsRepository: MarsRepository ): ViewModel() {


    private val _marsPhoto = MutableStateFlow<MarsPhoto?>(null)
    val marsPhoto=_marsPhoto.asStateFlow()

    val pageMarsPhoto:Flow<PagingData<Photo>> =Pager(
        config = PagingConfig(pageSize = 10),
        initialKey = null,
        pagingSourceFactory = {MarsPagginSource()}
    ).flow.cachedIn(viewModelScope)

}