package com.marc.rickmorty.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoute {

    @Serializable
    data object Characters : AppRoute

    @Serializable
    data class CharacterDetail(
        val characterId: Int
    ) : AppRoute
}