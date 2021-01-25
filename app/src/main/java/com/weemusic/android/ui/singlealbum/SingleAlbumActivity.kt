package com.weemusic.android.ui.singlealbum

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.weemusic.android.R
import com.weemusic.android.databinding.ActivitySingleAlbumBinding
import com.weemusic.android.domain.Album
import com.weemusic.android.ui.topalbums.KEY_SELECTED_ALBUM

class SingleAlbumActivity : AppCompatActivity() {
    private lateinit var mViewModel: SingleAlbumViewModel
    private lateinit var mAlbum: Album
    private lateinit var mBinding: ActivitySingleAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get album
        mAlbum = if (savedInstanceState == null) {
            intent.getSerializableExtra(KEY_SELECTED_ALBUM) as Album
        } else {
            savedInstanceState.getSerializable(KEY_SELECTED_ALBUM) as Album
        }

        // set up ViewModel
        mViewModel = ViewModelProvider(
            this,
            SingleAlbumViewModelFactory(mAlbum)
        ).get(SingleAlbumViewModel::class.java)

        // set up DataBinding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_album)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = mViewModel

        observeCover()
        setUpActionBar()
    }

    private fun setUpActionBar() {
        val toolbar: Toolbar = findViewById(R.id.single_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun observeCover() {
        mViewModel.getCover().observe(this, {
            // load cover art into View with calculated constraint
            mBinding.singleIvAlbumArt.post {
                // get the constraint necessary to make the cover art View a square
                val constraint = when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_PORTRAIT -> mBinding.singleIvAlbumArt.width
                    Configuration.ORIENTATION_LANDSCAPE -> mBinding.singleIvAlbumArt.height
                    else -> mBinding.singleIvAlbumArt.width
                }
                // load cover art
                Picasso
                    .with(this)
                    .load(it)
                    .resize(constraint, constraint)
                    .into(mBinding.singleIvAlbumArt)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_SELECTED_ALBUM, mAlbum)
    }
}