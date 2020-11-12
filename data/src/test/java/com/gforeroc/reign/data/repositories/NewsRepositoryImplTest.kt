package com.gforeroc.reign.data.repositories

import com.gforeroc.reign.data.base.MockableTest
import com.gforeroc.reign.data.models.APINews
import com.gforeroc.reign.data.models.APINewsResponse
import com.gforeroc.reign.data.network.NetworkClient
import com.gforeroc.reign.data.network.service.NewsService
import com.gforeroc.reign.domain.models.NewsItem
import com.gforeroc.reign.domain.repositories.INewsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class NewsRepositoryImplTest : MockableTest {

    @MockK
    lateinit var newsService: NewsService

    @MockK
    lateinit var networkClient: NetworkClient

    @MockK
    lateinit var responseAPI: Response<APINewsResponse>

    @Before
    override fun setup() {
        super.setup()
        coEvery {
            responseAPI.body()
        }.answers {
            APINewsResponse
        }

        coEvery {
            networkClient.apiCall(newsService.getNewsList())
        }.answers {
            responseAPI
        }

    }

    private val APINewsResponse =
        APINewsResponse(
            listOf(
                APINews(
                    "2020-11-09T13:52:55.000Z",
                    "Comic News",
                    "Comic news1",
                    "Gussi",
                    "https://nymag.com/intelligencer/2020/11/inside-the-new-york-times-heated-reckoning-with-itself.html"
                ), APINews(
                    "2020-11-09T13:52:55.000Z",
                    "Comic News2",
                    "Comic newss", "Gussi y And",
                    "https://nymag.com/intelligencer/2020/11/inside-the-new-york-times-heated-reckoning-with-itself.html"
                )
            )
        )

    private fun getRepositoryImpl(): INewsRepository {
        return NewsRepositoryImpl(networkClient, newsService)
    }

    @Test
    fun `should get news list`() {
        val repository = getRepositoryImpl()
        val response: List<NewsItem> =
            runBlocking {
                repository.getNews()
            }

        coVerify {
            networkClient.apiCall(
                newsService.getNewsList()
            )
        }
        Assert.assertEquals(response.first().title, "Comic News")
    }

}