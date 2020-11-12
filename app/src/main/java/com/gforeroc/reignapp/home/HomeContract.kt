package com.gforeroc.reignapp.home

import com.gforeroc.reign.domain.models.NewsItem
import com.gforeroc.reignapp.utils.BaseCoroutinePresenter
import com.gforeroc.reignapp.view.BaseView

interface HomeContract {
    interface View : BaseView {
        fun showLoading()
        fun hideLoading()
        fun setNewsList(news: ArrayList<NewsItem>)
        fun hideSwipeRefresh()
    }

    interface Presenter : BaseCoroutinePresenter<View> {
        fun getNews()
        fun setNewsList(news: ArrayList<NewsItem>? = null)

    }
}