package com.gforeroc.reign.data.mappers

import com.gforeroc.reign.data.models.APINews
import com.gforeroc.reign.data.utils.convertDateFromApiToAppFormat
import com.gforeroc.reign.data.utils.findMinutes
import com.gforeroc.reign.domain.models.NewsItem

object APINewsMapper {
    fun map(apiNews: APINews): NewsItem {
        val apiTitle = apiNews.story_title ?: apiNews.title
        val minutes = apiNews.created_at?.findMinutes()
        val date = apiNews.created_at?.convertDateFromApiToAppFormat() ?: "Date not found"
        return NewsItem(
            apiTitle ?: "Title not found",
            apiNews.author ?: "Author not found",
            date, apiNews.story_url, minutesAgo = minutes
        )
    }
}