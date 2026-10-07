package com.marc.rickmorty.core.common

sealed interface ResourceState<out T> {

    data object Loading : ResourceState<Nothing>

    data class Success<T>(
        val data: T
    ) : ResourceState<T>

    data class Error(
        val message: String? = null
    ) : ResourceState<Nothing>
}