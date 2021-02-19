package com.interview.album.data

import com.interview.ui.data.AlbumInfo

data class AlbumDetailDisplayInfo(
    val albumInfo: AlbumInfo,
    val showCarousel: Boolean = true
)