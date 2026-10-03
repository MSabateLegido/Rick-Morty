package com.marc.rickmorty

import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.domain.usecase.GetCharactersUseCase
import com.marc.rickmorty.features.characters.presentation.viewmodel.CharacterViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.collections.emptyList

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelTest {

    private lateinit var getCharactersUseCase: GetCharactersUseCase
    private lateinit var viewModel: CharacterViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getCharactersUseCase = mockk()
        viewModel = CharacterViewModel(getCharactersUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadCharacters updates uiState correctly when successful`() = runTest {
        val characters = listOf(
            Character(
                id = 1,
                name = "Rick Sanchez",
                status = "",
                species = "",
                type = "",
                image = "",
            ),
            Character(
                id = 2,
                name = "Morty Smith",
                status = "",
                species = "",
                type = "",
                image = "",
            )
        )
        coEvery {
            getCharactersUseCase()
        } returns characters
        viewModel.loadCharacters()
        advanceUntilIdle()

        assertEquals(characters, viewModel.uiState.value.characters)
        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(null, viewModel.uiState.value.error)
    }

    @Test
    fun `loadCharacters set error correctly when failure`() = runTest {
        val exception = Exception("Something went wrong")

        coEvery {
            getCharactersUseCase()
        } throws exception

        viewModel.loadCharacters()
        advanceUntilIdle()

        assertEquals(emptyList<Character>(), viewModel.uiState.value.characters)
        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(exception.message, viewModel.uiState.value.error)
    }
}