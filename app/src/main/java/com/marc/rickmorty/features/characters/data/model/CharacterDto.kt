package com.marc.rickmorty.features.characters.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val image: String
)
