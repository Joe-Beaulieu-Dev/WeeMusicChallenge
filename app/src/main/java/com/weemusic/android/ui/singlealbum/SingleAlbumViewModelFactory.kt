package com.weemusic.android.ui.singlealbum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weemusic.android.domain.Album

class SingleAlbumViewModelFactory(private val album: Album) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleAlbumViewModel::class.java)) {
            return SingleAlbumViewModel(album) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}