package com.marc.rickmorty.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.testing.asSnapshot
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.domain.usecase.GetCharactersUseCase
import com.marc.rickmorty.features.characters.presentation.viewmodel.CharacterViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class CharacterViewModelTest {

    private val getCharactersUseCase: GetCharactersUseCase = mockk()

    @Test
    fun `characters emits characters from use case`() = runTest {
        val characters = listOf(
            Character(
                id = 1,
                name = "Rick Sanchez",
                status = "",
                species = "",
                type = "",
                image = ""
            ),
            Character(
                id = 2,
                name = "Morty Smith",
                status = "",
                species = "",
                type = "",
                image = ""
            )
        )

        every {
            getCharactersUseCase()
        } returns Pager(
            config = PagingConfig(pageSize = 2),
            pagingSourceFactory = {
                object : PagingSource<Int, Character>() {

                    override suspend fun load(
                        params: LoadParams<Int>
                    ): LoadResult<Int, Character> {
                        return PagingSource.LoadResult.Page(
                            data = characters,
                            prevKey = null,
                            nextKey = null
                        )
                    }

                    override fun getRefreshKey(
                        state: PagingState<Int, Character>
                    ): Int? = null
                }
            }
        ).flow

        val viewModel = CharacterViewModel(getCharactersUseCase)

        val result = viewModel.characters.asSnapshot()

        Assert.assertEquals(characters, result)

        verify(exactly = 1) {
            getCharactersUseCase()
        }
    }
}