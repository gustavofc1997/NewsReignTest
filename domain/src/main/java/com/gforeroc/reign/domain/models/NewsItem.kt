package com.gforeroc.reign.domain.models

data class NewsItem(
    val title: String,
    val author: String,
    val creationDate: String,
    val storyUrl: String?,
    val minutesAgo: Long? = 0
)