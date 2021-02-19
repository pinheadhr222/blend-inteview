package com.interview.search.model

import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.interview.search.R
import kotlinx.android.synthetic.main.loading_view.view.*

@EpoxyModelClass
abstract class LoadingModel : EpoxyModelWithHolder<LoadingModel.LocalHolder>() {

    override fun getDefaultLayout(): Int = R.layout.loading_view

    override fun bind(holder: LocalHolder) {
            holder.loader.show()
    }

    inner class LocalHolder: EpoxyHolder() {
        lateinit var loader: ContentLoadingProgressBar

        override fun bindView(itemView: View) {
            loader= itemView.loading
        }
    }
}