package com.gforeroc.reignapp.home.adapter

import com.gforeroc.reign.domain.models.NewsItem

interface NewsAdapterListener {

    fun onNewClicked(
        news: NewsItem
    )
}