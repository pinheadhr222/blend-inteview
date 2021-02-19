package com.interview.search.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.interview.search.R
import com.interview.ui.data.AlbumInfo
import kotlinx.android.synthetic.main.album.view.*

@EpoxyModelClass
abstract class GalleryAlbum: EpoxyModelWithHolder<GalleryAlbum.LocalHolder>() {

    @EpoxyAttribute
    var album: AlbumInfo? = null

    override fun getDefaultLayout(): Int = R.layout.album

    override fun bind(holder: LocalHolder) {
        holder.title.text = album?.title
        holder.description.text = album?.description

        Glide.with(holder.image)
            .load(album?.coverImage)
            .into(holder.image)
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