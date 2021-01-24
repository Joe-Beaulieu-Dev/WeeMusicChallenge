package com.weemusic.android.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.weemusic.android.R
import com.weemusic.android.domain.Album
import kotlinx.android.synthetic.main.activity_main.*

class TopAlbumsActivity : AppCompatActivity() {
    private lateinit var mViewModel: TopAlbumsViewModel
    private lateinit var mAdapter: AlbumsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this
            , SavedStateViewModelFactory(application, this))
            .get(TopAlbumsViewModel::class.java)
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
        mAdapter.sortByAlbumNameAsc()
        rvFeed.adapter = mAdapter
        rvFeed.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sort, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_album -> {
                mAdapter.sortByAlbumNameAsc()
                true
            }
            R.id.action_artist -> {
                mAdapter.sortByArtistAsc()
                true
            }
            R.id.action_price -> {
                mAdapter.sortByPriceAsc()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
