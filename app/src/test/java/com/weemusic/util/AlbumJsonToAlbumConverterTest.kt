package com.weemusic.util

import com.weemusic.android.util.AlbumJsonToAlbumConverter
import com.weemusic.testutil.ALBUM_JSON
import com.weemusic.testutil.album1
import com.weemusic.testutil.getAlbumJson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AlbumJsonToAlbumConverterTest {
    @Test
    fun assertConverterProperlyConverts() {
        val album = AlbumJsonToAlbumConverter.jsonToAlbum(getAlbumJson(ALBUM_JSON))

        assertEquals(album1.id, album.id)
        assertEquals(album1.name, album.name)
        assertTrue(album1.images == album.images)
        assertEquals(album1.rights, album.rights)
        assertEquals(album1.title, album.title)
        assertEquals(album1.artist, album.artist)
        assertEquals(album1.category, album.category)
        assertEquals(album1.releaseDate.toString(), album.releaseDate.toString())
        assertEquals(album1.price, album.price)
    }
}