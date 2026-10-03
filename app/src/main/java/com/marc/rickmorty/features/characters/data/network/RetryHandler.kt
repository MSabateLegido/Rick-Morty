package com.marc.rickmorty.features.characters.data.network

import android.util.Log
import kotlinx.coroutines.delay
import retrofit2.HttpException


suspend fun <T> executeRetryAfter(
    maxTries: Int = 10,
    request: suspend () -> T
): T {
    var tries = 0
    while (true) {
        try {
            return request()
        } catch (e: HttpException) {
            if (e.code() != 429 || tries > maxTries) {
                throw e
            }
            val retryAfter = e.response()
                ?.headers()
                ?.get("Retry-After")
                ?.toLongOrNull()
                ?: throw e

            tries++

            delay((retryAfter + 1) * 1_000)
        }
    }
}
