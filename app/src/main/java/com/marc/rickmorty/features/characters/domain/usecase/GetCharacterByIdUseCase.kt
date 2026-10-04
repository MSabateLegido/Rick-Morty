package com.marc.rickmorty.features.characters.domain.usecase

import com.marc.rickmorty.features.characters.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(characterId: Int) =
        repository.getCharacterById(characterId)
}