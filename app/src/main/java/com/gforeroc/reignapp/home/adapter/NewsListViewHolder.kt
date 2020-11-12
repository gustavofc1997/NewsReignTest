package com.gforeroc.reignapp.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gforeroc.reign.domain.models.NewsItem
import com.gforeroc.reignapp.R

class NewsListViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView), BindViewHolder {

    constructor(parent: ViewGroup)
            : this(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_news_list,
            parent,
            false
        )
    )

    private val tvNewsTitle = itemView.findViewById<TextView>(R.id.tvNewsTitle)
    private val tvNewsInfo = itemView.findViewById<TextView>(R.id.tvDetails)

    override fun bind(newsItem: NewsItem, listener: NewsAdapterListener) {
        setupView(newsItem, listener)
    }

    private fun setupView(
        newsItem: NewsItem, listener: NewsAdapterListener
    ) {
        tvNewsTitle.text = newsItem.title
        tvNewsInfo.text = "${newsItem.author} ${"-"} ${newsItem.creationDate}"

        itemView.setOnClickListener {
            listener.onNewClicked(newsItem)
        }
    }
}