package com.marc.rickmorty.features.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.marc.rickmorty.features.characters.data.datasource.CharacterApi
import com.marc.rickmorty.features.characters.data.mapper.toDomain
import com.marc.rickmorty.features.characters.data.paging.CharacterPagingSource
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterApi: CharacterApi
) : CharacterRepository {

    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                CharacterPagingSource(characterApi)
            }
        ).flow.map { pagingData ->
            pagingData.map { characterDto ->
                characterDto.toDomain()
            }
        }
    }

    override suspend fun getCharacterById(characterId: Int): Character {
        return characterApi
            .getCharacterById(characterId)
            .toDomain()

    }
}