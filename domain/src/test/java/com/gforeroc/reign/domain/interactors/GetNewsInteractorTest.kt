package com.gforeroc.reign.domain.interactors

import com.gforeroc.reign.domain.base.MockableTest
import com.gforeroc.reign.domain.exceptions.NewsNotFoundException
import com.gforeroc.reign.domain.models.NewsItem
import com.gforeroc.reign.domain.models.None
import com.gforeroc.reign.domain.preferences.IDeletedNewsRepository
import com.gforeroc.reign.domain.repositories.INewsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetNewsInteractorTest : MockableTest {

    @MockK
    lateinit var newsRepository: INewsRepository

    @MockK
    lateinit var deletedRepository: IDeletedNewsRepository

    @MockK
    lateinit var failingNewsRepository: INewsRepository

    private val item =
        NewsItem("title", "Gus", "November 09, 2020", "https://github.com/", 9828, 9121)

    @Before
    override fun setup() {
        super.setup()
        coEvery { newsRepository.getNews() }.answers {
            listOf(item)
        }
        coEvery {
            deletedRepository.getItemsDeleted()
        }.answers {
            arrayListOf()
        }

        coEvery {
            failingNewsRepository.getNews()
        }.answers {
            throw NewsNotFoundException()
        }
    }

    @Test
    fun `get news item`() {
        val interactor =
            GetNewsInteractor(newsRepository, deletedRepository)
        val result =
            runBlocking {
                interactor.invoke(None)
            }
        Assert.assertEquals(listOf(item), result)
    }


    @Test(expected = NewsNotFoundException::class)
    fun `should throw exception`() {
        val interactor =
            GetNewsInteractor(failingNewsRepository, deletedRepository)
        val result = runBlocking {
            interactor(None)
        }

        coVerify {
            interactor.invoke(None)
        }

        Assert.assertTrue(result is Throwable)

    }
}