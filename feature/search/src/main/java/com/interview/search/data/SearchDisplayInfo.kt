package com.interview.search.data

import com.interview.ui.data.AlbumInfo

data class SearchDisplayInfo(
    val showLoading: Boolean = false,
    val showNoResult: Boolean = false,
    val query: String,
    val result: List<AlbumInfo>? = null
)