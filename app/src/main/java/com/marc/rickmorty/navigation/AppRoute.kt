package com.marc.rickmorty.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoute {

    @Serializable
    data object Characters : AppRoute
}