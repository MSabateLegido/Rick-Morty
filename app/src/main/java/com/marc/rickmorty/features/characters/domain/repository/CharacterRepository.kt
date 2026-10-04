package com.marc.rickmorty.features.characters.domain.repository

import androidx.paging.PagingData
import com.marc.rickmorty.features.characters.domain.model.Character
import kotlinx.coroutines.flow.Flow


interface CharacterRepository {

    fun getCharacters(): Flow<PagingData<Character>>

    suspend fun getCharacterById(characterId: Int): Character
}