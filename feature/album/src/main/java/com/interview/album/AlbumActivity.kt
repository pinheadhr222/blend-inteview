package com.interview.album

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.interview.ui.data.AlbumInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumActivity: AppCompatActivity() {

    companion object {
        const val ALBUM_INFO = "album_info"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_activity)
        if (savedInstanceState == null) {
            val albumInfo = intent?.getParcelableExtra<AlbumInfo>(ALBUM_INFO)

            albumInfo?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AlbumFragment.newInstance(albumInfo))
                    .commitNow()
            }
        }
    }
}