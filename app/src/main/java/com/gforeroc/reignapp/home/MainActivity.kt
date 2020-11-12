package com.gforeroc.reignapp.home

import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gforeroc.reign.domain.models.NewsItem
import com.gforeroc.reignapp.R
import com.gforeroc.reignapp.home.adapter.NewsAdapterListener
import com.gforeroc.reignapp.home.adapter.SwipeToDeleteCallback
import com.gforeroc.reignapp.view.BaseActivity
import com.gforeroc.reignapp.home.adapter.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), HomeContract.View, NewsAdapterListener,
    SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.bind(this)
        lifecycle.addObserver(presenter)
        initRecyclerView()
        swipeRefreshLayout?.setOnRefreshListener(this)
    }

    override fun tryAgainAction() {
        super.tryAgainAction()
        presenter.getNews()
    }

    override fun hideSwipeRefresh() {
        swipeRefreshLayout?.isRefreshing = false
    }

    private fun initRecyclerView() {
        newsAdapter = NewsAdapter(this)
        rvNews?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(false)
        }
        enableSwipeToDelete()
    }

    private fun enableSwipeToDelete() {
        val swipeToDeleteCallback: SwipeToDeleteCallback = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                newsAdapter.removeItem(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(rvNews)
    }


    override fun showLoading() {
        showProgressDialog()
    }

    override fun hideLoading() {
        hideProgressDialog()
    }

    override fun setNewsList(news: ArrayList<NewsItem>) {
        newsAdapter.setNews(news)
    }

    override fun onNewClicked(news: NewsItem) {
        news.storyUrl?.let {
            val customTabsBuilder = CustomTabsIntent.Builder()
            customTabsBuilder.setExitAnimations(
                this, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            customTabsBuilder.build().launchUrl(this, Uri.parse(it))
        }

    }

    override fun onRefresh() {
        presenter.getNews()
    }
}