package com.marc.rickmorty.features.characters.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterLocationDto(
    val name: String,
    val url: String
)
