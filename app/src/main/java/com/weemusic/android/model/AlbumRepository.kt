package com.weemusic.android.model

import com.google.gson.JsonObject
import com.weemusic.android.domain.GetTopAlbumsUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val KEY_FULL_RESPONSE = "feed"
private const val KEY_ALBUMS = "entry"

object AlbumRepository {

    fun getTopAlbums(getTopAlbumsUseCase: GetTopAlbumsUseCase): Single<List<JsonObject>> =
        getTopAlbumsUseCase
            .perform()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                response.getAsJsonObject(KEY_FULL_RESPONSE)
                    .getAsJsonArray(KEY_ALBUMS)
                    .map { it.asJsonObject }
            }
}