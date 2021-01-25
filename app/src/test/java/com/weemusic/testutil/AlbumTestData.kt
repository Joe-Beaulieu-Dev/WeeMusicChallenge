package com.weemusic.testutil

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.weemusic.android.domain.Album
import java.time.LocalDate

fun getAlbumJson(albumJsonString: String): JsonObject {
    return JsonParser().parse(albumJsonString).asJsonObject
}

const val ALBUM_JSON = "{\"im:name\":{\"label\":\"Starting Over\"},\"im:image\":[{\"label\":\"https://is2-ssl.mzstatic.com/image/thumb/Music114/v4/0e/e1/be/0ee1bebf-783b-787e-8f34-0d0df37b3f69/20UMGIM71875.rgb.jpg/55x55bb.png\",\"attributes\":{\"height\":\"55\"}},{\"label\":\"https://is1-ssl.mzstatic.com/image/thumb/Music114/v4/0e/e1/be/0ee1bebf-783b-787e-8f34-0d0df37b3f69/20UMGIM71875.rgb.jpg/60x60bb.png\",\"attributes\":{\"height\":\"60\"}},{\"label\":\"https://is5-ssl.mzstatic.com/image/thumb/Music114/v4/0e/e1/be/0ee1bebf-783b-787e-8f34-0d0df37b3f69/20UMGIM71875.rgb.jpg/170x170bb.png\",\"attributes\":{\"height\":\"170\"}}],\"im:itemCount\":{\"label\":\"14\"},\"im:price\":{\"label\":\"\$9.99\",\"attributes\":{\"amount\":\"9.99000\",\"currency\":\"USD\"}},\"im:contentType\":{\"im:contentType\":{\"attributes\":{\"term\":\"Album\",\"label\":\"Album\"}},\"attributes\":{\"term\":\"Music\",\"label\":\"Music\"}},\"rights\":{\"label\":\"A Mercury Nashville Release; ? 2020 Sound Records, under exclusive license to UMG Recordings, Inc.\"},\"title\":{\"label\":\"Starting Over - Chris Stapleton\"},\"link\":{\"attributes\":{\"rel\":\"alternate\",\"type\":\"text/html\",\"href\":\"https://music.apple.com/us/album/starting-over/1528423147?uo=2\"}},\"id\":{\"label\":\"https://music.apple.com/us/album/starting-over/1528423147?uo=2\",\"attributes\":{\"im:id\":\"1528423147\"}},\"im:artist\":{\"label\":\"Chris Stapleton\",\"attributes\":{\"href\":\"https://music.apple.com/us/artist/chris-stapleton/1752134?uo=2\"}},\"category\":{\"attributes\":{\"im:id\":\"6\",\"term\":\"Country\",\"scheme\":\"https://music.apple.com/us/genre/music-country/id6?uo=2\",\"label\":\"Country\"}},\"im:releaseDate\":{\"label\":\"2020-11-13T00:00:00-07:00\",\"attributes\":{\"label\":\"November 13, 2020\"}}}"

private const val ALBUM_IMAGE_SMALL = "https://is2-ssl.mzstatic.com/image/thumb/Music114/v4/0e/e1/be/0ee1bebf-783b-787e-8f34-0d0df37b3f69/20UMGIM71875.rgb.jpg/55x55bb.png"
private const val ALBUM_IMAGE_MEDIUM = "https://is1-ssl.mzstatic.com/image/thumb/Music114/v4/0e/e1/be/0ee1bebf-783b-787e-8f34-0d0df37b3f69/20UMGIM71875.rgb.jpg/60x60bb.png"
private const val ALBUM_IMAGE_LARGE = "https://is5-ssl.mzstatic.com/image/thumb/Music114/v4/0e/e1/be/0ee1bebf-783b-787e-8f34-0d0df37b3f69/20UMGIM71875.rgb.jpg/170x170bb.png"

const val ALBUM_ID: Int = 1528423147
val ALBUM_IMAGES: List<String> = ArrayList<String>().apply {
    add(ALBUM_IMAGE_SMALL)
    add(ALBUM_IMAGE_MEDIUM)
    add(ALBUM_IMAGE_LARGE)
}
const val ALBUM_RIGHTS: String = "A Mercury Nashville Release; ? 2020 Sound Records, under exclusive license to UMG Recordings, Inc."
const val ALBUM_TITLE: String = "Starting Over - Chris Stapleton"
const val ALBUM_CATEGORY: String = "Country"
val ALBUM_RELEASE_DATE: LocalDate = LocalDate.parse("2020-11-13")

val album1 = Album(
    ALBUM_ID,
    "Starting Over",
    ALBUM_IMAGES,
    ALBUM_RIGHTS,
    ALBUM_TITLE,
    "Chris Stapleton",
    ALBUM_CATEGORY,
    ALBUM_RELEASE_DATE,
    "$9.99"
)

val album2 = Album(
    ALBUM_ID,
    "T",
    ALBUM_IMAGES,
    ALBUM_RIGHTS,
    ALBUM_TITLE,
    "D",
    ALBUM_CATEGORY,
    ALBUM_RELEASE_DATE,
    "$10.00"
)

val album3 = Album(
    ALBUM_ID,
    "U",
    ALBUM_IMAGES,
    ALBUM_RIGHTS,
    ALBUM_TITLE,
    "E",
    ALBUM_CATEGORY,
    ALBUM_RELEASE_DATE,
    "$11.00"
)

val album4 = Album(
    ALBUM_ID,
    "V",
    ALBUM_IMAGES,
    ALBUM_RIGHTS,
    ALBUM_TITLE,
    "F",
    ALBUM_CATEGORY,
    ALBUM_RELEASE_DATE,
    "$12.00"
)

val album5 = Album(
    ALBUM_ID,
    "W",
    ALBUM_IMAGES,
    ALBUM_RIGHTS,
    ALBUM_TITLE,
    "G",
    ALBUM_CATEGORY,
    ALBUM_RELEASE_DATE,
    "$13.00"
)

val album6 = Album(
    ALBUM_ID,
    "X",
    ALBUM_IMAGES,
    ALBUM_RIGHTS,
    ALBUM_TITLE,
    "H",
    ALBUM_CATEGORY,
    ALBUM_RELEASE_DATE,
    "$14.00"
)

val album7 = Album(
    ALBUM_ID,
    "Y",
    ALBUM_IMAGES,
    ALBUM_RIGHTS,
    ALBUM_TITLE,
    "I",
    ALBUM_CATEGORY,
    ALBUM_RELEASE_DATE,
    "$15.00"
)

val album8 = Album(
    ALBUM_ID,
    "Z",
    ALBUM_IMAGES,
    ALBUM_RIGHTS,
    ALBUM_TITLE,
    "J",
    ALBUM_CATEGORY,
    ALBUM_RELEASE_DATE,
    "$16.00"
)

val album9 = Album(
    ALBUM_ID,
    "ZA",
    ALBUM_IMAGES,
    ALBUM_RIGHTS,
    ALBUM_TITLE,
    "K",
    ALBUM_CATEGORY,
    ALBUM_RELEASE_DATE,
    "$17.00"
)

val album10 = Album(
    ALBUM_ID,
    "ZB",
    ALBUM_IMAGES,
    ALBUM_RIGHTS,
    ALBUM_TITLE,
    "L",
    ALBUM_CATEGORY,
    ALBUM_RELEASE_DATE,
    "$18.00"
)