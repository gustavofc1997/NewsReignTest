package com.gforeroc.reign.data.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.gforeroc.reign.data.helpers.LocalStorageHelper
import com.gforeroc.reign.data.helpers.NetworkHelper
import com.gforeroc.reign.data.network.NetworkClient
import com.gforeroc.reign.data.network.NetworkClientImpl
import com.gforeroc.reign.domain.helpers.ILocalStorageHelper
import com.gforeroc.reign.domain.helpers.INetworkHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HelperModule {

    private val key = "com.reign.news.preferences"

    @Provides
    @Singleton
    fun provideNetworkClient(): NetworkClient {
        return NetworkClientImpl()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(key, MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(context: Context): INetworkHelper = NetworkHelper(context)


    @Provides
    @Singleton
    fun provideLocalStorageHelper(preferences: SharedPreferences): ILocalStorageHelper =
        LocalStorageHelper(preferences)


}