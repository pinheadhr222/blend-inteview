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
abstract class ImageDetailModel : EpoxyModelWithHolder<ImageDetailModel.LocalHolder>() {

    @EpoxyAttribute
    var image: AlbumImageInfo? = null

    override fun getDefaultLayout(): Int = R.layout.carousel_image_detail

    override fun bind(holder: LocalHolder) {
        holder.title.text = image?.title

        holder.description.text = image?.description

        image?.let {
            Glide.with(holder.image)
                .load(image?.link)
                .into(holder.image)
        }
    }

    inner class LocalHolder: EpoxyHolder() {
        lateinit var title: TextView
        lateinit var image: ImageView
        lateinit var description: TextView

        override fun bindView(itemView: View) {
            title= itemView.title
            image = itemView.image
            description = itemView.description
        }
    }
}