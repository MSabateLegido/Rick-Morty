package com.marc.rickmorty.features.characters.domain.model

data class CharacterFilters(
    val name: String? = null,
    val status: Status? = null,
    val gender: Gender? = null,
    val species: String? = null,
    val type: String? = null
)
