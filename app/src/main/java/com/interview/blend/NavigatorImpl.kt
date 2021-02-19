package com.interview.blend

import android.content.Context
import android.content.Intent
import com.interview.album.AlbumActivity
import com.interview.ui.Action
import com.interview.ui.Navigator
import com.interview.ui.data.AlbumInfo

class NavigatorImpl: Navigator {
    override fun route(context: Context, action: Action) {
        val intent = when(action) {
            is Action.AlbumAction -> buildAlbumIntent(context, action.albumInfo)
        }

        context.startActivity(intent)
    }

    private fun buildAlbumIntent(context: Context, albumInfo: AlbumInfo): Intent {
        return Intent(context, AlbumActivity::class.java).apply {
//            putExtra(AlbumActivity.ALBUM_INFO, albumInfo)
        }
    }
}