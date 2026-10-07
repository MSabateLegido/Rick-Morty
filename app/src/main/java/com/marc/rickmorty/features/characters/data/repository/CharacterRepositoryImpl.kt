package com.marc.rickmorty.features.characters.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.marc.rickmorty.core.network.executeRetryAfter
import com.marc.rickmorty.features.characters.data.datasource.CharacterApi
import com.marc.rickmorty.features.characters.data.mapper.toDomain
import com.marc.rickmorty.features.characters.data.paging.CharacterPagingSource
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.domain.model.CharacterFilters
import com.marc.rickmorty.features.characters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterApi: CharacterApi
) : CharacterRepository {

    private val characterCache = mutableMapOf<Int, Character>()
    private val characterCacheMutex = Mutex()

    override fun getCharacters(
        filters: CharacterFilters
    ): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                CharacterPagingSource(
                    api = characterApi,
                    filters = filters
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { characterDto ->
                val character = characterDto.toDomain()

                characterCacheMutex.withLock {
                    characterCache[character.id] = character
                }

                character
            }
        }
    }

    override suspend fun getCharacterById(characterId: Int): Character {
        val cachedCharacter = characterCacheMutex.withLock {
            characterCache[characterId]
        }

        if (cachedCharacter != null) {
            return cachedCharacter
        }

        val character = executeRetryAfter {
            characterApi
                .getCharacterById(characterId)
                .toDomain()
        }

        characterCacheMutex.withLock {
            characterCache[character.id] = character
        }

        return character
    }
}