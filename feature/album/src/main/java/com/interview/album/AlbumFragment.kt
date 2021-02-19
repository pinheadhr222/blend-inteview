package com.interview.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.epoxy.carousel
import com.interview.album.model.AlbumImageModel_
import com.interview.album.model.albumHeader
import com.interview.ui.data.AlbumInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.album_fragment.*

@AndroidEntryPoint
class AlbumFragment: Fragment() {

    companion object {
        private const val CAROUSEL_ID = "images_carousel_container"
        fun newInstance(albumInfo: AlbumInfo): AlbumFragment {
            val args = Bundle()
            args.putParcelable(AlbumActivity.ALBUM_INFO, albumInfo)

            val frag =  AlbumFragment()
            frag.arguments = args

            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.album_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val albumInfo: AlbumInfo? = arguments?.getParcelable(AlbumActivity.ALBUM_INFO)

        albumInfo?.let {
            bindAlbumInfo(it)
        }
    }

    private fun bindAlbumInfo(albumInfo: AlbumInfo) {
        base_container.withModels {
            albumHeader {
                id(albumInfo.id)
                album(albumInfo)
            }

            carousel {
                id(CAROUSEL_ID)
                models(albumInfo.images.map {
                    AlbumImageModel_()
                        .id(it.id)
                        .image(it)
                })
            }
        }
    }

}