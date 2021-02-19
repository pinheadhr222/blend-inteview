package com.interview.album

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment: Fragment() {

    companion object {
        fun newInstance() = AlbumFragment()
    }

}