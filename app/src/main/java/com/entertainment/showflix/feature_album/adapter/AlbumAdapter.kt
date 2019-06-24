package com.entertainment.showflix.feature_album.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.entertainment.showflix.R
import com.entertainment.showflix.databinding.MovieItemBinding
import com.entertainment.showflix.feature_album.model.Album

class AlbumAdapter(private var context: Context, private var albums: List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.AlbumsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val binding: MovieItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)
        return AlbumsViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(albums[holder.adapterPosition])
        holder.binding.shimmerViewContainer.visibility = View.VISIBLE
        holder.binding.shimmerViewContainer.startShimmer()

        Glide.with(context)
            .load(albums[position].thumbnailUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("Glide", "Failure")
                    holder.binding.shimmerViewContainer.stopShimmer()
                    holder.binding.shimmerViewContainer.visibility = View.GONE
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("Glide", "Success")
                    holder.binding.shimmerViewContainer.stopShimmer()
                    holder.binding.title.text = albums[position].title
                    holder.binding.title.visibility = View.VISIBLE
                    holder.binding.shimmerViewContainer.visibility = View.GONE
                    return false
                }

            })
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.movieImage)
    }


    class AlbumsViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.album = album
            binding.executePendingBindings()
        }

    }

    private fun getImageUrl(album: Album) = "${album.thumbnailUrl}/FFFFFF/?text=${album.title}"
}
