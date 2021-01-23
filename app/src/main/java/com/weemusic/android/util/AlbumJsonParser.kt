package com.weemusic.android.util

import com.google.gson.JsonObject
import org.threeten.bp.LocalDate

private const val KEY_ID_OUTER = "id"
private const val KEY_ID_ATTRIBUTES = "attributes"
private const val KEY_ID_INNER = "im:id"
private const val KEY_ALBUM_NAME = "im:name"
private const val KEY_ALBUM_NAME_LABEL = "label"
private const val KEY_IMAGES = "im:image"
private const val KEY_IMAGES_LABEL = "label"
private const val KEY_RIGHTS = "rights"
private const val KEY_RIGHTS_LABEL = "label"
private const val KEY_TITLE = "title"
private const val KEY_TITLE_LABEL = "label"
private const val KEY_ARTIST = "im:artist"
private const val KEY_ARTIST_LABEL = "label"
private const val KEY_CATEGORY = "category"
private const val KEY_CATEGORY_ATTRIBUTES = "attributes"
private const val KEY_CATEGORY_LABEL = "label"
private const val KEY_RELEASE_DATE = "im:releaseDate"
private const val KEY_RELEASE_DATE_LABEL = "label"
private const val KEY_PRICE = "im:price"
private const val KEY_PRICE_LABEL = "label"

class AlbumJsonParser(private val album: JsonObject) {
    fun getId(): Int =
        album.getAsJsonObject(KEY_ID_OUTER)
            .getAsJsonObject(KEY_ID_ATTRIBUTES)
            .getAsJsonPrimitive(KEY_ID_INNER)
            .asInt

    fun getName(): String =
        album.getAsJsonObject(KEY_ALBUM_NAME)
            .getAsJsonPrimitive(KEY_ALBUM_NAME_LABEL)
            .asString

    fun getImages(): List<String> {
        val imageList = ArrayList<String>()
        val jsonImageList = album.getAsJsonArray(KEY_IMAGES)
        jsonImageList.forEach {
            imageList.add(
                it
                    .asJsonObject
                    .getAsJsonPrimitive(KEY_IMAGES_LABEL)
                    .asString
            )
        }
        return imageList
    }

    fun getRights(): String =
        album.getAsJsonObject(KEY_RIGHTS)
            .getAsJsonPrimitive(KEY_RIGHTS_LABEL)
            .asString

    fun getTitle(): String =
        album.getAsJsonObject(KEY_TITLE)
            .getAsJsonPrimitive(KEY_TITLE_LABEL)
            .asString

    fun getArtist(): String =
        album.getAsJsonObject(KEY_ARTIST)
            .getAsJsonPrimitive(KEY_ARTIST_LABEL)
            .asString

    fun getCategory(): String =
        album.getAsJsonObject(KEY_CATEGORY)
            .getAsJsonObject(KEY_CATEGORY_ATTRIBUTES)
            .getAsJsonPrimitive(KEY_CATEGORY_LABEL)
            .asString

//    fun getReleaseDate(): LocalDate =
//        album.getAsJsonObject(KEY_RELEASE_DATE)
//            .getAsJsonPrimitive(KEY_RELEASE_DATE_LABEL)
//            .asString

    fun getPrice(): String =
        album.getAsJsonObject(KEY_PRICE)
            .getAsJsonPrimitive(KEY_PRICE_LABEL)
            .asString
}