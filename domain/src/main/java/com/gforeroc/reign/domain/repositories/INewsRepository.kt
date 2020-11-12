package com.gforeroc.reign.domain.repositories

import com.gforeroc.reign.domain.models.NewsItem

interface INewsRepository {
    suspend fun getNews(): List<NewsItem>
}