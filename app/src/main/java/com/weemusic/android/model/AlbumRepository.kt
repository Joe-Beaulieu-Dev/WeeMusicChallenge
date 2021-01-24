package com.weemusic.android.model

import com.weemusic.android.domain.Album
import com.weemusic.android.domain.GetTopAlbumsUseCase
import com.weemusic.android.util.AlbumJsonToAlbumConverter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val KEY_FULL_RESPONSE = "feed"
private const val KEY_ALBUMS = "entry"

object AlbumRepository {
    fun getTopAlbums(mGetTopAlbumsUseCase: GetTopAlbumsUseCase): Single<List<Album>> =
        mGetTopAlbumsUseCase
            .perform()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                response.getAsJsonObject(KEY_FULL_RESPONSE)
                    .getAsJsonArray(KEY_ALBUMS)
                    .map { AlbumJsonToAlbumConverter.jsonToAlbum(it.asJsonObject) }
            }
}