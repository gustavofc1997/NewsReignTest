package com.gforeroc.reign.domain.interactors

import com.gforeroc.reign.domain.interactors.base.BaseInteractor
import com.gforeroc.reign.domain.models.NewsItem
import com.gforeroc.reign.domain.models.None
import com.gforeroc.reign.domain.preferences.IDeletedNewsRepository
import com.gforeroc.reign.domain.repositories.INewsRepository

class GetNewsInteractor(
    private val newsRepository: INewsRepository,
    private val deletesRepository: IDeletedNewsRepository
) :
    BaseInteractor<List<NewsItem>, None> {
    override suspend fun invoke(params: None): List<NewsItem> {
        val itemsDeleted = deletesRepository.getItemsDeleted()
        return newsRepository.getNews().filter { item ->
            itemsDeleted.contains(item.storyId).not()
        }
    }

}