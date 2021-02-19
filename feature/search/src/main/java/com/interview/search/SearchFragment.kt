package com.interview.search

import android.app.SearchManager
import android.content.res.Configuration
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
import com.interview.search.model.loading
import com.interview.search.model.noResult
import com.interview.search.vm.SearchViewModel
import com.interview.ui.Action
import com.interview.ui.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.search_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    @Inject lateinit var navigator: Navigator

    private lateinit var controller: GridController

    companion object {
        private const val LOADER_ID = "search_loader_id"
        private const val NO_RESULTS_ID = "no_results_id"

        private const val PORTRAIT_GRID_SPAN_SIZE = 2
        private const val LAND_GRID_SPAN_SIZE = 3

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

        //Determine the number of item of show on a row according to the orientation
        val spanSize = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LAND_GRID_SPAN_SIZE
            Configuration.ORIENTATION_PORTRAIT -> PORTRAIT_GRID_SPAN_SIZE
            else -> PORTRAIT_GRID_SPAN_SIZE
        }

        //Configure the Epoxy container, which include the LayoutManager and the controller for
        //create the model to add to the view.
        val layoutManager = GridLayoutManager(context, spanSize)
        val spacingDecorator = EpoxyItemSpacingDecorator(resources.getDimension(R.dimen.item_spacing).toInt())

        controller = GridController()
        controller.spanCount = spanSize

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
            //Show loading
            controller.displayInfo = SearchDisplayInfo(showLoading = true, query = query)
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

                if(it.showLoading) {
                    loading{
                        id(LOADER_ID)
                    }
                }

                //Show No Result no result was returned
                if (it.showNoResult) {
                    noResult {
                        id(NO_RESULTS_ID)
                        text(getString(R.string.not_results, it.query))
                    }
                }

                it.result?.forEach { album ->
                    galleryAlbum {
                        id(album.id)
                        album(album)

                        if (album.clickable) {
                            listener(View.OnClickListener {
                                val action = Action.ViewAlbum(album)
                                navigator.route(requireActivity(), action)
                            })
                        }
                    }
                }
            }
        }
    }

}