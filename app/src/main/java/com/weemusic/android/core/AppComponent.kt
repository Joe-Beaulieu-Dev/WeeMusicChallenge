package com.weemusic.android.core

import com.weemusic.android.ui.topalbums.TopAlbumsViewModel
import dagger.Component

@AppScope
@Component(dependencies = [DomainComponent::class])
interface AppComponent {
    fun inject(viewModel: TopAlbumsViewModel)
}