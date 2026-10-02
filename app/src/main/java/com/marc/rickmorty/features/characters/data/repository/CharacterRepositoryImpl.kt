package com.marc.rickmorty.features.characters.data.repository

import com.marc.rickmorty.features.characters.data.datasource.CharacterApi
import com.marc.rickmorty.features.characters.data.mapper.toDomain
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterApi: CharacterApi
) : CharacterRepository {

    override suspend fun getCharacters(): List<Character> {
        return characterApi
            .getCharacters()
            .results
            .map { it.toDomain() }
    }
}