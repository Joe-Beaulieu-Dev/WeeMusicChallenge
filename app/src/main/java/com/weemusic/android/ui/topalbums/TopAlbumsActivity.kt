package com.weemusic.android.ui.topalbums

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.weemusic.android.R
import com.weemusic.android.domain.Album
import kotlinx.android.synthetic.main.activity_main.*

private const val KEY_SORTING_METHOD = "sortingMethod"
private const val KEY_SORT_BY_ALBUM = 0
private const val KEY_SORT_BY_ARTIST = 1
private const val KEY_SORT_BY_PRICE = 2

class TopAlbumsActivity : AppCompatActivity() {
    private lateinit var mViewModel: TopAlbumsViewModel
    private lateinit var mAdapter: AlbumsAdapter
    private var mSortingMethod = KEY_SORT_BY_ALBUM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(TopAlbumsViewModel::class.java)
        lifecycle.addObserver(mViewModel)

        setUpActionBar()
    }

    private fun setUpActionBar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onStart() {
        super.onStart()
        observeAlbums()
    }

    private fun observeAlbums() {
        mViewModel.getTopAlbumsUseCase().observe(this, {
            setUpRecyclerView(it)
        })
    }

    private fun setUpRecyclerView(albums: List<Album>) {
        mAdapter = AlbumsAdapter(albums)
        sortAlbums(mSortingMethod)
        rvFeed.adapter = mAdapter
        rvFeed.layoutManager = getProperLayoutManager()
    }

    private fun getProperLayoutManager(): GridLayoutManager =
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> GridLayoutManager(this, 2)
            Configuration.ORIENTATION_LANDSCAPE -> GridLayoutManager(this, 3)
            else -> GridLayoutManager(this, 2)
        }

    private fun sortAlbums(sortingMethod: Int) {
        mSortingMethod = sortingMethod
        when (mSortingMethod) {
            KEY_SORT_BY_ALBUM -> mAdapter.sortByAlbumNameAsc()
            KEY_SORT_BY_ARTIST -> mAdapter.sortByArtistAsc()
            KEY_SORT_BY_PRICE -> mAdapter.sortByPriceAsc()
            else -> mAdapter.sortByAlbumNameAsc()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sort, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_album -> {
                sortAlbums(KEY_SORT_BY_ALBUM)
                true
            }
            R.id.action_artist -> {
                sortAlbums(KEY_SORT_BY_ARTIST)
                true
            }
            R.id.action_price -> {
                sortAlbums(KEY_SORT_BY_PRICE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_SORTING_METHOD, mSortingMethod)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mSortingMethod = savedInstanceState.getInt(KEY_SORTING_METHOD, KEY_SORT_BY_ALBUM)
    }
}
