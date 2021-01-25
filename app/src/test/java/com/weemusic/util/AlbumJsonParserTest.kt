package com.weemusic.util

import com.weemusic.android.util.AlbumJsonParser
import com.weemusic.testutil.ALBUM_JSON
import com.weemusic.testutil.album1
import com.weemusic.testutil.getAlbumJson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AlbumJsonParserTest {
    @Test
    fun assertParserProperlyParses() {
        val parser = AlbumJsonParser(getAlbumJson(ALBUM_JSON))

        assertEquals(album1.id, parser.getId())
        assertEquals(album1.name, parser.getName())
        assertTrue(album1.images == parser.getImages())
        assertEquals(album1.rights, parser.getRights())
        assertEquals(album1.title, parser.getTitle())
        assertEquals(album1.artist, parser.getArtist())
        assertEquals(album1.category, parser.getCategory())
        assertEquals(album1.releaseDate.toString(), parser.getReleaseDate().toString())
        assertEquals(album1.price, parser.getPrice())
    }
}