package com.marc.rickmorty.features.characters.domain.usecase

import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    val repository: CharacterRepository
) {

    suspend operator fun invoke(): List<Character> =
        repository.getCharacters()
}