package com.marc.rickmorty.features.characters.domain.repository

import com.marc.rickmorty.features.characters.domain.model.Character


interface CharacterRepository {

    suspend fun getCharacters(): List<Character>
}