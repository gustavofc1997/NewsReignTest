package com.gforeroc.reign.data.di

import android.content.Context
import com.gforeroc.reign.data.helpers.INetworkHelper
import com.gforeroc.reign.data.network.NetworkClient
import com.gforeroc.reign.data.network.NetworkClientImpl
import com.gforeroc.reign.data.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HelperModule {

    @Provides
    @Singleton
    fun provideNetworkClient(): NetworkClient {
        return NetworkClientImpl()
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(context: Context): INetworkHelper = NetworkHelper(context)
}