package com.gforeroc.reignapp.di

import com.gforeroc.reign.data.di.RepositoryModule
import com.gforeroc.reign.domain.interactors.DeleteNewsInteractor
import com.gforeroc.reign.domain.interactors.GetNewsInteractor
import com.gforeroc.reign.domain.interactors.base.BaseInteractor
import com.gforeroc.reign.domain.models.NewsItem
import com.gforeroc.reign.domain.models.None
import com.gforeroc.reign.domain.preferences.IDeletedNewsRepository
import com.gforeroc.reign.domain.repositories.INewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class InteractorsModule {
    @Singleton
    @Provides
    fun provideNewsInteractor(
        repository: INewsRepository,
        deletedNewsRepository: IDeletedNewsRepository
    ): BaseInteractor<List<NewsItem>, None> {
        return GetNewsInteractor(
            repository, deletedNewsRepository
        )
    }

    @Singleton
    @Provides
    fun provideDeleteNewsInteractor(
        repository: IDeletedNewsRepository
    ): BaseInteractor<Unit, Long> {
        return DeleteNewsInteractor(
            repository
        )
    }

}