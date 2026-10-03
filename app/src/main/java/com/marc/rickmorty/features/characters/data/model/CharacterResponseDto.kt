package com.marc.rickmorty.features.characters.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponseDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)