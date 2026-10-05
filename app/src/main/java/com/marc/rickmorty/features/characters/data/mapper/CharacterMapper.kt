package com.marc.rickmorty.features.characters.data.mapper

import com.marc.rickmorty.features.characters.data.model.CharacterDto
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.domain.model.Gender
import com.marc.rickmorty.features.characters.domain.model.Status

fun CharacterDto.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        status = when (status) {
            "Alive" -> Status.ALIVE
            "Dead" -> Status.DEAD
            else -> Status.UNKNOWN
        },
        species = species,
        type = type,
        gender = when (gender) {
            "Male" -> Gender.MALE
            "Female" -> Gender.FEMALE
            "Genderless" -> Gender.GENDERLESS
            else -> Gender.UNKNOWN
        },
        origin = origin.toDomain(),
        location = location.toDomain(),
        image = image,
        episode = episode
    )
}