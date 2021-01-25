package com.weemusic.android.ui.topalbums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weemusic.android.R
import com.weemusic.android.domain.Album
import com.weemusic.android.util.AlbumSorter
import kotlinx.android.synthetic.main.album_view_holder.view.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class AlbumsAdapter(private var albums: List<Album>, private var listener: AlbumListener) :
    RecyclerView.Adapter<AlbumsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.album_view_holder, parent, false)

        return AlbumsViewHolder(itemView, albums, listener)
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) =
        holder.onBind(albums[position])

    fun sortByAlbumNameAsc() {
        albums = AlbumSorter.sortByAlbumNameAsc(albums)
        notifyDataSetChanged()
    }

    fun sortByArtistAsc() {
        albums = AlbumSorter.sortByArtistAsc(albums)
        notifyDataSetChanged()
    }

    fun sortByPriceAsc() {
        albums = AlbumSorter.sortByPriceAsc(albums)
        notifyDataSetChanged()
    }
}

interface AlbumListener {
    fun onClick(album: Album)
}

class AlbumsViewHolder(
    itemView: View,
    private val albums: List<Album>,
    private val listener: AlbumListener
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

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

        itemView.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        listener.onClick(albums[adapterPosition])
    }
}