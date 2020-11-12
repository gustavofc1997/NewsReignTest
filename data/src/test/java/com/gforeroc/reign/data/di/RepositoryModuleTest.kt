package com.gforeroc.reign.data.di

import com.gforeroc.reign.data.base.MockableTest
import com.gforeroc.reign.data.di.RepositoryModule
import com.gforeroc.reign.data.network.NetworkClient
import com.gforeroc.reign.data.network.service.NewsService
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class RepositoryModuleTest : MockableTest {

    @MockK
    lateinit var service: NewsService

    @MockK
    lateinit var networkClient: NetworkClient

    @Before
    override fun setup() {
        super.setup()
    }

    @Test
    fun `should provide news repository`() {
        val module =
            RepositoryModule()

        val repository =
            module.providesNewsRepository(newsService = service, networkClient = networkClient)

        Assert.assertNotNull(repository)

    }

}
