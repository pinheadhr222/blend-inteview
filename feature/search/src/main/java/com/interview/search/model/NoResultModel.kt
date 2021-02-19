package com.interview.search.model

import android.view.View
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.interview.search.R
import kotlinx.android.synthetic.main.loading_view.view.*
import kotlinx.android.synthetic.main.no_result_view.view.*

@EpoxyModelClass
abstract class NoResultModel : EpoxyModelWithHolder<NoResultModel.LocalHolder>() {

    @EpoxyAttribute
    var text: String? = null

    override fun getDefaultLayout(): Int = R.layout.no_result_view

    override fun bind(holder: LocalHolder) {
        text?.let {
            holder.text.text = text
        }
    }

    inner class LocalHolder: EpoxyHolder() {
        lateinit var text: TextView

        override fun bindView(itemView: View) {
            text = itemView.text
        }
    }
}