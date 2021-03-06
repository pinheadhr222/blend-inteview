package com.interview.album.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.interview.album.R
import com.interview.ui.data.AlbumImageInfo
import kotlinx.android.synthetic.main.album_header.view.*

@EpoxyModelClass
abstract class AlbumImageModel : EpoxyModelWithHolder<AlbumImageModel.LocalHolder>() {

    @EpoxyAttribute
    var image: AlbumImageInfo? = null

    @EpoxyAttribute
    var listener: View.OnClickListener? = null

    override fun getDefaultLayout(): Int = R.layout.carousel_image

    override fun bind(holder: LocalHolder) {
        holder.image.setOnClickListener(listener)

        Glide.with(holder.image)
            .load(image?.link)
            .into(holder.image)
    }

    inner class LocalHolder: EpoxyHolder() {
        lateinit var image: ImageView

        override fun bindView(itemView: View) {
            image = itemView.image
        }
    }
}