package com.weemusic.android.util

import com.google.gson.JsonObject
import com.weemusic.android.domain.Album

object AlbumJsonToAlbumConverter {
    fun jsonToAlbum(albumJson: JsonObject): Album {
        val parser = AlbumJsonParser(albumJson)
        return Album(
            parser.getId(),
            parser.getName(),
            parser.getImages(),
            parser.getRights(),
            parser.getTitle(),
            parser.getArtist(),
            parser.getCategory(),
            parser.getReleaseDate(),
            parser.getPrice()
        )
    }
}