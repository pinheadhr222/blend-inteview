package com.interview.ui.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumInfo(
    val id: String,
    val title: String,
    val description: String,
    val coverImage: String,
    val favorite: Boolean,
    val images: List<AlbumImageInfo>
): Parcelable

@Parcelize
data class AlbumImageInfo(
    val id: String,
    val title: String,
    val description: String,
    val link: String,
    val type: String,
    val animated: Boolean,
    val favorite: Boolean
): Parcelable