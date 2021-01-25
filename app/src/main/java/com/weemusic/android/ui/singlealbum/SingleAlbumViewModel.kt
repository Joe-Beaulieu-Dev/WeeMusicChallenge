package com.weemusic.android.ui.singlealbum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weemusic.android.domain.Album

class SingleAlbumViewModel(album: Album) : ViewModel() {
    private val mCover = MutableLiveData(album.images[album.images.size - 1])
    val mAlbumName = MutableLiveData(album.name)
    val mArtist = MutableLiveData(album.artist)
    val mAlbumCategory = MutableLiveData(album.category)
    val mReleaseDate = MutableLiveData(album.releaseDate.toString())
    val mPrice = MutableLiveData(album.price)

    fun getCover(): LiveData<String> {
        return mCover
    }
}