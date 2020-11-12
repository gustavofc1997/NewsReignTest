package com.gforeroc.reignapp.di

import com.gforeroc.reign.data.di.RepositoryModule
import com.gforeroc.reign.domain.interactors.GetNewsInteractor
import com.gforeroc.reign.domain.interactors.base.BaseInteractor
import com.gforeroc.reign.domain.models.NewsItem
import com.gforeroc.reign.domain.repositories.INewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class InteractorsModule {
    @Singleton
    @Provides
    fun provideNewsInteractor(
        repository: INewsRepository
    ): BaseInteractor<List<NewsItem>> {
        return GetNewsInteractor(
            repository
        )
    }

}