package com.gforeroc.reign.data.repositories

import com.gforeroc.reign.data.mappers.APINewsMapper
import com.gforeroc.reign.data.network.NetworkClient
import com.gforeroc.reign.data.network.service.NewsService
import com.gforeroc.reign.domain.exceptions.NewsNotFoundException
import com.gforeroc.reign.domain.models.NewsItem
import com.gforeroc.reign.domain.repositories.INewsRepository

class NewsRepositoryImpl(
    private val networkClient: NetworkClient,
    private val newsService: NewsService
) : INewsRepository {
    override suspend fun getNews(): List<NewsItem> {
        val response =
            networkClient.apiCall(newsService.getNewsList()).body()?.let {
                it
            } ?: throw NewsNotFoundException()

        return response.hits.map {
            APINewsMapper.map(it)
        }
    }
}