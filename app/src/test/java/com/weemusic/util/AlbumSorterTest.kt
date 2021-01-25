package com.weemusic.util

import com.weemusic.android.domain.Album
import com.weemusic.android.util.AlbumSorter
import com.weemusic.testutil.*
import org.junit.Assert.assertTrue
import org.junit.Test

class AlbumSorterTest {
    private val albumsUnsorted = ArrayList<Album>().apply {
        add(album3)
        add(album6)
        add(album9)
        add(album4)
        add(album10)
        add(album1)
        add(album8)
        add(album2)
        add(album7)
        add(album5)
    }

    // these albums have been set up in such a way that the sorted order
    // for name, artist, and price will all perfectly line up in this way
    private val albumsSorted = ArrayList<Album>().apply {
        add(album1)
        add(album2)
        add(album3)
        add(album4)
        add(album5)
        add(album6)
        add(album7)
        add(album8)
        add(album9)
        add(album10)
    }

    @Test
    fun assertSorterProperlySorts_byAlbumNameAsc() {
        assertTrue(albumsSorted == AlbumSorter.sortByAlbumNameAsc(albumsUnsorted))
    }

    @Test
    fun assertSorterProperlySorts_byArtistAsc() {
        assertTrue(albumsSorted == AlbumSorter.sortByArtistAsc(albumsUnsorted))
    }

    @Test
    fun assertSorterProperlySorts_byPriceAsc() {
        assertTrue(albumsSorted == AlbumSorter.sortByPriceAsc(albumsUnsorted))
    }
}