package com.gforeroc.reignapp.home

import com.gforeroc.reign.domain.interactors.base.BaseInteractor
import com.gforeroc.reign.domain.models.CoroutinesContextProvider
import com.gforeroc.reign.domain.models.NewsItem
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    fun providesHomePresenter(
        coroutinesContextProvider: CoroutinesContextProvider,
        getNewsInteractor: BaseInteractor<List<NewsItem>>
    ): HomeContract.Presenter {
        return HomePresenter(coroutinesContextProvider, getNewsInteractor)
    }
}