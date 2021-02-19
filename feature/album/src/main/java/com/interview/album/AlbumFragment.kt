package com.interview.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.carousel
import com.interview.album.data.AlbumDetailDisplayInfo
import com.interview.album.model.AlbumImageModel_
import com.interview.album.model.albumHeader
import com.interview.album.model.imageDetail
import com.interview.album.vm.AlbumDetailViewModel
import com.interview.ui.data.AlbumInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.album_fragment.*

@AndroidEntryPoint
class AlbumFragment: Fragment() {
    private val controller = LocalEpoxyController()

    private val viewModel: AlbumDetailViewModel by activityViewModels()

    companion object {
        private const val CAROUSEL_ID = "images_carousel_container"
        private const val IMAGE_DETAIL_ID = "image_detail_container"

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

        base_container.setController(controller)

        viewModel.data.observe(viewLifecycleOwner, {
            controller.displayInfo = it
        })

        val albumInfo: AlbumInfo? = arguments?.getParcelable(AlbumActivity.ALBUM_INFO)

        albumInfo?.let {
            viewModel.init(it)
        }
    }

    inner class LocalEpoxyController : AsyncEpoxyController() {
        private var selectedCarouselImagePos = 0

        var displayInfo: AlbumDetailDisplayInfo? = null
            set(value) {
                field = value
                requestModelBuild()
            }

        override fun buildModels() {
            displayInfo?.let { displayInfo ->

                albumHeader {
                    id(displayInfo.albumInfo.id)
                    album(displayInfo.albumInfo)
                }

                if (displayInfo.showCarousel) {
                    carousel {
                        id(CAROUSEL_ID)
                        models(displayInfo.albumInfo.images.mapIndexed { index, image ->
                            AlbumImageModel_()
                                .id(image.id)
                                .image(image)
                                .listener(View.OnClickListener {
                                    selectedCarouselImagePos = index
                                    requestModelBuild()
                                })
                        })
                    }
                }

                imageDetail {
                    id(IMAGE_DETAIL_ID)
                    image(displayInfo.albumInfo.images[selectedCarouselImagePos])
                }
            }
        }
    }

}