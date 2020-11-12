package com.gforeroc.reign.data

import com.gforeroc.reign.data.base.MockableTest
import com.gforeroc.reign.data.network.NetworkClientImpl
import com.gforeroc.reign.data.utils.HTTP_NOT_FOUND
import com.gforeroc.reign.domain.exceptions.NetworkException
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class NetworkClientImplTest : MockableTest {

    @MockK
    lateinit var notFoundResponse: Response<Any>

    @MockK
    lateinit var badRequestResponse: Response<Any>

    @Before
    override fun setup() {
        super.setup()

        every {
            notFoundResponse.isSuccessful
        }.answers {
            false
        }
        every {
            notFoundResponse.code()
        }.answers {
            HTTP_NOT_FOUND
        }

        every {
            notFoundResponse.errorBody()
                ?.string()
        }.answers {
            "{\"statusCode\": 404, \"error\":forgot"
        }

        every {
            badRequestResponse.isSuccessful
        }.answers {
            false
        }

        every { badRequestResponse.code() }
            .answers {
                400
            }

        every {
            badRequestResponse.errorBody()
                ?.string()
        }.answers {
            "{\"statusCode\": 400, \"error\":\"no parameter\""
        }
    }

    @Test
    fun `should throw Server Error`() {
        val networkClient =
            NetworkClientImpl()


        val call = mockk<Response<Any>>()

        every {
            call.isSuccessful
        }.answers {
            false
        }

        every {
            call.code()
        }.answers {
            500
        }

        every {
            call.errorBody()?.string()
        }.answers {
            "{\"statusCode\": 500, \"error\":\"fake error\""
        }

        runBlocking {
            try {
                networkClient.apiCall(CompletableDeferred(call))
            } catch (throwable: Throwable) {
                assert(throwable is NetworkException.ServerError)
            }
        }

    }


    @Test(expected = NetworkException.NotFound::class)
    fun `should throw not found exception`() {
        val networkClient =
            NetworkClientImpl()

        runBlocking {
            networkClient.apiCall(CompletableDeferred(notFoundResponse))
        }

    }


    @Test
    fun `should throw Unknown Error`() {
        val networkClient =
            NetworkClientImpl()


        val call = mockk<Response<Any>>()

        every {
            call.isSuccessful
        }.answers {
            false
        }

        every {
            call.code()
        }.answers {
            -1
        }

        every {
            call.errorBody()?.string()
        }.answers {
            "{\"statusCode\": -1, \"error\":\"fake error\""
        }


        runBlocking {
            try {
                networkClient.apiCall(CompletableDeferred(call))
            } catch (throwable: Throwable) {
                assert(throwable is UnknownError)
            }
        }

    }

    @Test
    fun `should return response`() {
        val networkClient =
            NetworkClientImpl()

        val fakeResponse = mockk<Response<Any>>()

        every {
            fakeResponse.isSuccessful
        }.answers {
            true
        }

        every {
            fakeResponse.code()
        }.answers {
            200
        }


        runBlocking {
            val result = networkClient.apiCall(CompletableDeferred(fakeResponse))
            assert(result.code() == 200)
        }
    }
}
