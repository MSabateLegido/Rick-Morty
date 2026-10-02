package com.marc.rickmorty.features.characters.data.mapper

import com.marc.rickmorty.features.characters.data.model.CharacterDto
import com.marc.rickmorty.features.characters.domain.model.Character

fun CharacterDto.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        image = image
    )
}