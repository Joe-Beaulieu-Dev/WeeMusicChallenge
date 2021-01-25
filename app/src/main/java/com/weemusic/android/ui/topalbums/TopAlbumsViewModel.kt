package com.weemusic.android.ui.topalbums

import androidx.lifecycle.*
import com.weemusic.android.core.DaggerAppComponent
import com.weemusic.android.core.DaggerDomainComponent
import com.weemusic.android.core.DaggerNetworkComponent
import com.weemusic.android.domain.Album
import com.weemusic.android.domain.GetTopAlbumsUseCase
import com.weemusic.android.model.AlbumRepository
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class TopAlbumsViewModel : ViewModel(), LifecycleObserver {
    @Inject
    lateinit var mGetTopAlbumsUseCase: GetTopAlbumsUseCase
    private var mAlbums: MutableLiveData<List<Album>> = MutableLiveData(emptyList())
    private lateinit var mTopAlbumsDisposable: Disposable

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        val networkComponent = DaggerNetworkComponent.create()
        val domainComponent = DaggerDomainComponent
            .builder()
            .networkComponent(networkComponent)
            .build()

        DaggerAppComponent
            .builder()
            .domainComponent(domainComponent)
            .build()
            .inject(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        mTopAlbumsDisposable = AlbumRepository.getTopAlbums(mGetTopAlbumsUseCase)
            .subscribe(Consumer {
            mAlbums.value = it
        })
    }

    fun getTopAlbumsUseCase(): LiveData<List<Album>> = mAlbums

    override fun onCleared() {
        mTopAlbumsDisposable.dispose()
        super.onCleared()
    }
}