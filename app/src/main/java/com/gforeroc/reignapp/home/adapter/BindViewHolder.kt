package com.gforeroc.reignapp.home.adapter

import com.gforeroc.reign.domain.models.NewsItem

interface BindViewHolder {
    fun bind(
        newsItem: NewsItem,
        listener: NewsAdapterListener
    )
}