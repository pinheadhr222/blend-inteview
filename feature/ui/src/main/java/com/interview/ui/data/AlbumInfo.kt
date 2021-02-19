package com.interview.ui.data

data class AlbumInfo(
    val id: String,
    val title: String,
    val description: String,
    val coverImage: String,
    val favorite: Boolean,
    val images: List<AlbumImageInfo>
)

data class AlbumImageInfo(
    val id: String,
    val title: String,
    val description: String,
    val link: String,
    val type: String,
    val animated: Boolean,
    val favorite: Boolean
)