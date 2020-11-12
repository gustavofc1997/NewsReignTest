package com.gforeroc.reignapp.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gforeroc.reign.domain.models.NewsItem


class NewsAdapter(
    private val newsAdapterListener: NewsAdapterListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: ArrayList<NewsItem> = arrayListOf()

    fun setNews(news: ArrayList<NewsItem>) {
        this.items = news
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BindViewHolder).bind(items[position], newsAdapterListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsListViewHolder(parent)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItemAtPosition(position: Int): NewsItem {
        return items[position]
    }

    override fun getItemCount() = items.size

}
