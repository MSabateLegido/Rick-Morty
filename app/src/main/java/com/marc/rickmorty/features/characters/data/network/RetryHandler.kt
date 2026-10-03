package com.marc.rickmorty.features.characters.data.network

import android.util.Log
import kotlinx.coroutines.delay
import retrofit2.HttpException



suspend fun <T> executeRetryAfter(
    maxRetries: Int = 3,
    request: suspend () -> T
): T {
    var tries = 0
    while (true) {
        try {
            return request()
        } catch (e: HttpException) {
            if (e.code() != 429) {
                throw e
            }
            Log.d("Paging", "HTTP error: 429 from RetryHandler. Try number $tries.")
            val retryAfter = e.response()
                ?.headers()
                ?.get("Retry-After")
                ?.toLongOrNull()
                ?: throw e
            Log.d("Paging", "Waiting $retryAfter seconds")

            tries++

            delay((retryAfter + 1) * 1_000)
        }
    }
}
