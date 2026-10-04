package com.marc.rickmorty.network

import com.marc.rickmorty.features.characters.data.network.executeRetryAfter
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class RetryHandlerTest {

    @Test
    fun `executeRetryAfter retries request after retry-after delay`() = runTest {
        val exception = mockk<HttpException>()
        val response = mockk<Response<Any>>()

        every { exception.code() } returns 429
        every { exception.response() } returns response
        every { response.headers()["Retry-After"] } returns "2"

        var attempts = 0
        val delays = mutableListOf<Long>()

        val result = executeRetryAfter(
            delayMillis = { delays.add(it) },
            request = {
                attempts++

                if (attempts == 1) {
                    throw exception
                }

                "success"
            }
        )

        assertEquals("success", result)
        assertEquals(2, attempts)
        assertEquals(listOf(3_000L), delays)
    }

    @Test
    fun `executeRetryAfter does not retry when exception is not 429`() = runTest {
        val exception = mockk<HttpException>()

        every { exception.code() } returns 500

        var attempts = 0

        try {
            executeRetryAfter(
                delayMillis = {
                    fail("Delay should not be called")
                },
                request = {
                    attempts++
                    throw exception
                }
            )
        } catch (_: HttpException) {
        }

        assertEquals(1, attempts)
    }

    @Test
    fun `executeRetryAfter does not retry when Retry-After header is missing`() = runTest {
        val exception = mockk<HttpException>()
        val response = mockk<Response<Any>>()

        every { exception.code() } returns 429
        every { exception.response() } returns response
        every { response.headers()["Retry-After"] } returns null

        var attempts = 0

        try {
            executeRetryAfter(
                delayMillis = {
                    fail("Delay should not be called")
                },
                request = {
                    attempts++
                    throw exception
                }
            )
        } catch (_: HttpException) {
        }

        assertEquals(1, attempts)
    }

    @Test
    fun `executeRetryAfter stops retrying after max tries`() = runTest {
        val exception = mockk<HttpException>()
        val response = mockk<Response<Any>>()

        every { exception.code() } returns 429
        every { exception.response() } returns response
        every { response.headers()["Retry-After"] } returns "1"

        var attempts = 0

        try {
            executeRetryAfter(
                maxTries = 3,
                delayMillis = {},
                request = {
                    attempts++
                    throw exception
                }
            )
        } catch (_: HttpException) {
        }

        assertEquals(4, attempts)
    }
}