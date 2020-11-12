package com.gforeroc.reign.data.di

import com.gforeroc.reign.data.network.NetworkClient
import com.gforeroc.reign.data.network.service.NewsService
import com.gforeroc.reign.data.repositories.NewsRepositoryImpl
import com.gforeroc.reign.domain.repositories.INewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ServiceProviderModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun providesNewsRepository(
        networkClient: NetworkClient,
        newsService: NewsService
    ): INewsRepository {
        return NewsRepositoryImpl(networkClient, newsService)
    }
}