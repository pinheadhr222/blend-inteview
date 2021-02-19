package com.interview.ui

import android.content.Context
import com.interview.ui.data.AlbumInfo

interface Navigator {

    fun route(context: Context, action: Action)
}


sealed class Action {
    data class  ViewAlbum(val albumInfo: AlbumInfo): Action()
}