package com.gforeroc.reignapp.home

import com.gforeroc.reign.domain.interactors.base.BaseInteractor
import com.gforeroc.reign.domain.models.CoroutinesContextProvider
import com.gforeroc.reign.domain.models.NewsItem
import com.gforeroc.reign.domain.models.None
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    fun providesHomePresenter(
        coroutinesContextProvider: CoroutinesContextProvider,
        getNewsInteractor: BaseInteractor<List<NewsItem>, None>,
        deleteNewsInteractor: BaseInteractor<Unit, Long>
    ): HomeContract.Presenter {
        return HomePresenter(coroutinesContextProvider, getNewsInteractor, deleteNewsInteractor)
    }
}