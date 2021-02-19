package com.interview.album.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.interview.album.data.AlbumDetailDisplayInfo
import com.interview.ui.data.AlbumInfo

class AlbumDetailViewModel: ViewModel() {

    private val _data = MutableLiveData<AlbumDetailDisplayInfo>()
    val data: LiveData<AlbumDetailDisplayInfo> = _data

    fun init(albumInfo: AlbumInfo) {
        _data.value = AlbumDetailDisplayInfo(
            albumInfo = albumInfo,
            showCarousel = albumInfo.images.size > 1
        )
    }
}