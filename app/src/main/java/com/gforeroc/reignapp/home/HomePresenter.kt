package com.gforeroc.reignapp.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.gforeroc.reign.domain.interactors.base.BaseInteractor
import com.gforeroc.reign.domain.models.CoroutinesContextProvider
import com.gforeroc.reign.domain.models.NewsItem
import com.gforeroc.reign.domain.models.None
import com.gforeroc.reignapp.utils.IErrorHandler
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class HomePresenter(
    override val coroutinesContextProvider: CoroutinesContextProvider,
    private val getNewsInteractor: BaseInteractor<List<NewsItem>, None>,
    private val deleteNewsInteractor: BaseInteractor<Unit, Long>
) :
    HomeContract.Presenter {

    override val parentJob: Job = Job()
    override var errorHandler: IErrorHandler? = null
    override var view: HomeContract.View? = null
    override val subscriptions: CompositeDisposable? = CompositeDisposable()
    private var news: ArrayList<NewsItem>? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun getNews() {
        view?.showLoading()
        launchJobOnMainDispatchers {
            try {
                val list = withContext(coroutinesContextProvider.backgroundContext) {
                    getNewsInteractor(None)
                }
                val array = arrayListOf<NewsItem>()
                array.addAll(list)
                setNewsList(array)
            } catch (t: Throwable) {
                handleException(t)
                setNewsList()
            }
            view?.hideSwipeRefresh()
        }
    }

    override fun setNewsList(news: ArrayList<NewsItem>?) {
        this.news = news
        view?.hideLoading()
        news?.let {
            view?.setNewsList(it)
        }
    }

    override fun deleteNews(id: Long) {
        view?.showLoading()
        launchJobOnMainDispatchers {
            withContext(coroutinesContextProvider.backgroundContext) {
                deleteNewsInteractor(id)
            }
            view?.hideLoading()
        }
    }


}