package com.weemusic.android.util

import com.weemusic.android.domain.Album

object AlbumSorter {
    fun sortByAlbumNameAsc(albums: List<Album>): List<Album> = albums.sortedBy { it.name }

    fun sortByArtistAsc(albums: List<Album>): List<Album> = albums.sortedBy { it.artist }

    fun sortByPriceAsc(albums: List<Album>): List<Album> =
        albums.sortedBy { it.price.substring(1).toDoubleOrNull() }
}