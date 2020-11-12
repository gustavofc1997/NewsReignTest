package com.gforeroc.reign.data.di

import android.content.Context
import com.gforeroc.reign.data.base.MockableTest
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class HelperModuleTest : MockableTest {

    @MockK
    lateinit var context: Context

    @Before
    override fun setup() {
        super.setup()
    }

    @Test
    fun `should provide net client`() {
        val module =
            HelperModule()

        val netClient =
            module.provideNetworkClient()

        Assert.assertNotNull(netClient)

    }

    @Test
    fun `should provide network helper`() {
        val module =
            HelperModule()

        val netClient =
            module.provideNetworkHelper(context)

        Assert.assertNotNull(netClient)

    }

}
