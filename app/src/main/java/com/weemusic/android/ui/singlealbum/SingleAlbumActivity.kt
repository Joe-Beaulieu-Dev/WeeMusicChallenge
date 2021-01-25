package com.weemusic.android.ui.singlealbum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        mAlbum = if (savedInstanceState == null) {
            intent.getSerializableExtra(KEY_SELECTED_ALBUM) as Album
        } else {
            savedInstanceState.getSerializable(KEY_SELECTED_ALBUM) as Album
        }

        mViewModel = ViewModelProvider(
            this,
            SingleAlbumViewModelFactory(mAlbum)
        ).get(SingleAlbumViewModel::class.java)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_album)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = mViewModel

        observeCover()
    }

    private fun observeCover() {
        mViewModel.getCover().observe(this, {
            mBinding.singleIvAlbumArt.post {
                Picasso
                    .with(this)
                    .load(it)
                    .resize(mBinding.singleIvAlbumArt.width, mBinding.singleIvAlbumArt.width)
                    .into(mBinding.singleIvAlbumArt)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_SELECTED_ALBUM, mAlbum)
    }
}