package com.interview.search.data

import com.interview.ui.data.AlbumInfo

data class SearchDisplayInfo(
    val query: String,
    val result: List<AlbumInfo>
)