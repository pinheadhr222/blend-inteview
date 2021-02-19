package com.interview.search

import android.app.SearchManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyItemSpacingDecorator
import com.interview.search.data.SearchDisplayInfo
import com.interview.search.model.galleryAlbum
import com.interview.search.vm.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.search_fragment.*

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var controller: GridController

    companion object {
        private const val GRID_SPAN_SIZE = 2

        fun newInstance(query: String): SearchFragment {
            val args = Bundle()
            args.putString(SearchManager.QUERY, query)

            val fragment = SearchFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private  val viewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Configure the Epoxy container
        val layoutManager = GridLayoutManager(context, GRID_SPAN_SIZE)
        val spacingDecorator = EpoxyItemSpacingDecorator(resources.getDimension(R.dimen.item_spacing).toInt())

        controller = GridController()
        controller.spanCount = GRID_SPAN_SIZE

        base_container.adapter = controller.adapter
        base_container.layoutManager = layoutManager
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        base_container.addItemDecoration(spacingDecorator)

        viewModel.data.observe(viewLifecycleOwner, {
            controller.displayInfo = it
        })

        val query = arguments?.getString(SearchManager.QUERY, "")

        query?.let {
            viewModel.search(query)
        }
    }

    inner class GridController: AsyncEpoxyController() {
        var displayInfo: SearchDisplayInfo? = null
            set(value) {
                field = value
                requestModelBuild()
            }

        override fun buildModels() {
            displayInfo?.let {
                it.result.forEach { album ->
                    galleryAlbum {
                        id(album.id)
                        album(album)
                    }
                }
            }
        }
    }

}