package com.marc.rickmorty.features.characters.data.mapper

import com.marc.rickmorty.features.characters.data.model.CharacterLocationDto
import com.marc.rickmorty.features.characters.domain.model.CharacterLocation


fun CharacterLocationDto.toDomain(): CharacterLocation {
    return CharacterLocation(
        name = name,
        url = url
    )
}