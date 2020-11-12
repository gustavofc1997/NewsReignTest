package com.gforeroc.reign.data.di

import android.content.Context
import com.gforeroc.reign.domain.helpers.INetworkHelper
import com.gforeroc.reign.data.network.interceptors.OfflineConnectionInterceptor
import com.gforeroc.reign.data.network.interceptors.OnlineConnectionInterceptor
import com.gforeroc.reign.data.network.service.NewsService
import com.gforeroc.reign.data.utils.SERVICE_BASE_URL
import com.gforeroc.reign.data.utils.TIMEOUT_SECONDS
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [(HelperModule::class)])
class ServiceProviderModule {

    @Provides
    @Singleton
    fun provideBaseClient(
        networkHelper: INetworkHelper, context: Context
    ):
            OkHttpClient {
        val httpCacheDirectory = File(context.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())
        val builder = OkHttpClient.Builder().cache(cache)
            .addInterceptor(OfflineConnectionInterceptor(networkHelper))
            .addNetworkInterceptor(OnlineConnectionInterceptor())
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideBaseRetrofit(
        baseOkHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SERVICE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(baseOkHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }
}
