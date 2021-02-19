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
import com.interview.ui.data.AlbumInfo
import kotlinx.android.synthetic.main.album_header.view.*

@EpoxyModelClass
abstract class AlbumHeaderModel : EpoxyModelWithHolder<AlbumHeaderModel.LocalHolder>() {

    @EpoxyAttribute
    var album: AlbumInfo? = null

    @EpoxyAttribute
    var listener: View.OnClickListener? = null

    override fun getDefaultLayout(): Int = R.layout.album_header

    override fun bind(holder: LocalHolder) {
        holder.parent.setOnClickListener(listener)
        holder.title.text = album?.title
        holder.description.text = album?.description

        Glide.with(holder.image)
            .load(album?.coverImage)
            .into(holder.image)
    }

    inner class LocalHolder: EpoxyHolder() {
        lateinit var parent: View
        lateinit var title: TextView
        lateinit var image: ImageView
        lateinit var description: TextView

        override fun bindView(itemView: View) {
            parent = itemView.album_header
            title= itemView.title
            image = itemView.image
            description = itemView.description
        }
    }
}