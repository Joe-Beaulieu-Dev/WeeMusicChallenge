package com.weemusic.android.ui

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import com.weemusic.android.R
import com.weemusic.android.core.DaggerAppComponent
import com.weemusic.android.core.DaggerDomainComponent
import com.weemusic.android.core.DaggerNetworkComponent
import com.weemusic.android.domain.Album
import com.weemusic.android.domain.GetTopAlbumsUseCase
import com.weemusic.android.util.AlbumJsonToAlbumConverter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.album_view_holder.view.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit.DAYS
import javax.inject.Inject

class TopAlbumsActivity : AppCompatActivity() {
    @Inject
    lateinit var mGetTopAlbumsUseCase: GetTopAlbumsUseCase
    private lateinit var mAlbums: List<Album>
    private lateinit var mAdapter: AlbumsAdapter
    private lateinit var mTopAlbumsDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpActionBar()
        initDaggerComponents()
    }

    override fun onStart() {
        super.onStart()
        mTopAlbumsDisposable = mGetTopAlbumsUseCase
            .perform()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                response.getAsJsonObject("feed")
                    .getAsJsonArray("entry")
                    .map { it.asJsonObject }
            }
            .subscribe(Consumer {
                mAlbums = jsonListToAlbumList(it)
                mAdapter = AlbumsAdapter(sortByAlbumNameAsc(jsonListToAlbumList(it)))
                rvFeed.adapter = mAdapter
                rvFeed.layoutManager = GridLayoutManager(this, 2)
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sort, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_album -> {
                mAdapter.albums = sortByAlbumNameAsc(mAlbums)
                mAdapter.notifyDataSetChanged()
                return true
            }
            R.id.action_artist -> {
                mAdapter.albums = sortByArtistAsc(mAlbums)
                mAdapter.notifyDataSetChanged()
                return true
            }
            R.id.action_price -> {
                mAdapter.albums = sortByPriceAsc(mAlbums)
                mAdapter.notifyDataSetChanged()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpActionBar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initDaggerComponents() {
        val networkComponent = DaggerNetworkComponent.create()
        val domainComponent = DaggerDomainComponent
            .builder()
            .networkComponent(networkComponent)
            .build()

        DaggerAppComponent
            .builder()
            .domainComponent(domainComponent)
            .build()
            .inject(this)
    }

    private fun jsonListToAlbumList(jsonAlbumList: List<JsonObject>): List<Album> {
        val albumList = ArrayList<Album>()
        jsonAlbumList.forEach {
            albumList.add(jsonToAlbum(it))
        }
        return albumList
    }

    private fun jsonToAlbum(albumJson: JsonObject): Album {
        return AlbumJsonToAlbumConverter.jsonToAlbum(albumJson)
    }

    private fun sortByAlbumNameAsc(list: List<Album>): List<Album> {
        return list.sortedBy { it.name }
    }

    private fun sortByArtistAsc(list: List<Album>): List<Album> {
        return list.sortedBy { it.artist }
    }

    private fun sortByPriceAsc(list: List<Album>): List<Album> {
        return list.sortedBy { it.price.substring(1).toDoubleOrNull() }
    }

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
            val daysOld = DAYS.between(album.releaseDate, LocalDate.now())
            return daysOld <= 30
        }
    }
}
