package com.gforeroc.reign.domain.interactors

import com.gforeroc.reign.domain.interactors.base.BaseInteractor
import com.gforeroc.reign.domain.models.NewsItem
import com.gforeroc.reign.domain.repositories.INewsRepository

class GetNewsInteractor(private val newsRepository: INewsRepository) :
    BaseInteractor<List<NewsItem>> {
    override suspend fun invoke(): List<NewsItem> {
        return newsRepository.getNews().sortedBy { it.minutesAgo }
    }
}