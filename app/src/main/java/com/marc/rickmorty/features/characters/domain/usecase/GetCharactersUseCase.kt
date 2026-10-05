package com.marc.rickmorty.features.characters.domain.usecase

import androidx.paging.PagingData
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.domain.model.CharacterFilters
import com.marc.rickmorty.features.characters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(
        filters: CharacterFilters
    ): Flow<PagingData<Character>> =
        repository.getCharacters(filters)
}