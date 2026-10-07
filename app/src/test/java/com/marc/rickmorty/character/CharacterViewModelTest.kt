package com.marc.rickmorty.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.testing.asSnapshot
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.domain.model.CharacterFilters
import com.marc.rickmorty.features.characters.domain.model.CharacterLocation
import com.marc.rickmorty.features.characters.domain.model.Gender
import com.marc.rickmorty.features.characters.domain.model.Status
import com.marc.rickmorty.features.characters.domain.usecase.GetCharactersUseCase
import com.marc.rickmorty.features.characters.presentation.characters.viewmodel.CharacterViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelTest {

    private val getCharactersUseCase: GetCharactersUseCase = mockk()

    @Test
    fun `characters loads with default filters`() = runTest {
        val pagingData = PagingData.empty<Character>()

        every {
            getCharactersUseCase(CharacterFilters())
        } returns flowOf(pagingData)

        val viewModel = CharacterViewModel(getCharactersUseCase)

        viewModel.characters.first()

        verify(exactly = 1) {
            getCharactersUseCase(CharacterFilters())
        }
    }

    @Test
    fun `applying filters reloads characters`() = runTest {
        every {
            getCharactersUseCase(any())
        } returns flowOf(PagingData.empty())

        val viewModel = CharacterViewModel(getCharactersUseCase)

        viewModel.characters.first()

        val filters = CharacterFilters(
            // els camps que tinguis
        )

        viewModel.updateEditingFilters(filters)
        viewModel.applyFilters()

        viewModel.characters.first()

        verify {
            getCharactersUseCase(filters)
        }
    }
}