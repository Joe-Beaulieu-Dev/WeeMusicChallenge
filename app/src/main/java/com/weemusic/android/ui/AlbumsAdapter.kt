package com.weemusic.android.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weemusic.android.R
import com.weemusic.android.domain.Album
import kotlinx.android.synthetic.main.album_view_holder.view.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class AlbumsAdapter(var albums: List<Album>) : RecyclerView.Adapter<AlbumsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.album_view_holder, parent, false)

        return AlbumsViewHolder(itemView)
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) =
        holder.onBind(albums[position])

    fun sortByAlbumNameAsc() {
        albums = albums.sortedBy { it.name }
        notifyDataSetChanged()
    }

    fun sortByArtistAsc() {
        albums = albums.sortedBy { it.artist }
        notifyDataSetChanged()
    }

    fun sortByPriceAsc() {
        albums = albums.sortedBy { it.price.substring(1).toDoubleOrNull() }
        notifyDataSetChanged()
    }
}

class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(album: Album) {
        val coverUrl = album.images.last()
        val name = album.name
        val artist = album.artist
        val price = album.price

        val ivCover: ImageView = itemView.findViewById(R.id.ivCover)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvArtist: TextView = itemView.findViewById(R.id.tvArtist)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)

        // Resize cover art. Should be a square and take up the entire width of the itemView.
        // The measured width at runtime will be required to set the height, and therefore
        // cannot be done preemptively in xml
        ivCover.post {
            Picasso
                .with(itemView.context)
                .load(coverUrl)
                .resize(ivCover.width, ivCover.width)
                .into(ivCover)
        }
        tvName.text = name
        tvArtist.text = artist
        tvPrice.text = price
        setNewIconVisibility(album)
    }

    private fun setNewIconVisibility(album: Album) {
        if (isAlbumNew(album)) {
            itemView.tvNewAlbum.visibility = View.VISIBLE
        } else {
            itemView.tvNewAlbum.visibility = View.INVISIBLE
        }
    }

    private fun isAlbumNew(album: Album): Boolean {
        val daysOld = ChronoUnit.DAYS.between(album.releaseDate, LocalDate.now())
        return daysOld <= 30
    }
}