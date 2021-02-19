package com.interview.search

import android.app.SearchManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.interview.search.vm.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    companion object {
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

        val query = arguments?.getString(SearchManager.QUERY, "")

        query?.let {
            viewModel.search(query)
        }
    }

}