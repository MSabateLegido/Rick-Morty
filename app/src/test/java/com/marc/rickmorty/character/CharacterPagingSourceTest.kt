package com.marc.rickmorty.character

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marc.rickmorty.features.characters.data.datasource.CharacterApi
import com.marc.rickmorty.features.characters.data.model.CharacterDto
import com.marc.rickmorty.features.characters.data.model.CharacterLocationDto
import com.marc.rickmorty.features.characters.data.model.CharacterResponseDto
import com.marc.rickmorty.features.characters.data.model.InfoDto
import com.marc.rickmorty.features.characters.data.paging.CharacterPagingSource
import com.marc.rickmorty.features.characters.domain.model.CharacterFilters
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.cancellation.CancellationException

class CharacterPagingSourceTest {

    private val api: CharacterApi = mockk()
    private val characters = listOf(
        CharacterDto(
            id = 1,
            name = "Rick Sanchez",
            status = "",
            species = "",
            type = "",
            gender = "",
            origin = CharacterLocationDto(
                name = "",
                url = ""
            ),
            location = CharacterLocationDto(
                name = "",
                url = ""
            ),
            image = "",
            episode = listOf()
        )
    )

    private val filters = CharacterFilters()

    @Test
    fun `load returns page with characters and correct keys`() = runTest {

        coEvery {
            api.getCharacters(1)
        } returns CharacterResponseDto(
            info = InfoDto(
                count = 10,
                pages = 2,
                next = "https://rickandmortyapi.com/api/character?page=2",
                prev = null
            ),
            results = characters
        )

        val pagingSource = CharacterPagingSource(api, filters)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = characters,
                prevKey = null,
                nextKey = 2
            ),
            result
        )
    }

    @Test
    fun `load returns correct keys for middle page`() = runTest {

        coEvery {
            api.getCharacters(2)
        } returns CharacterResponseDto(
            info = InfoDto(
                count = 10,
                pages = 3,
                next = "https://rickandmortyapi.com/api/character?page=3",
                prev = "https://rickandmortyapi.com/api/character?page=1"
            ),
            results = characters
        )

        val pagingSource = CharacterPagingSource(api, filters)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 2,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = characters,
                prevKey = 1,
                nextKey = 3
            ),
            result
        )
    }

    @Test
    fun `load returns null next key for last page`() = runTest {

        coEvery {
            api.getCharacters(3)
        } returns CharacterResponseDto(
            info = InfoDto(
                count = 5,
                pages = 3,
                next = null,
                prev = "https://rickandmortyapi.com/api/character?page=2"
            ),
            results = characters
        )

        val pagingSource = CharacterPagingSource(api, filters)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 3,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = characters,
                prevKey = 2,
                nextKey = null
            ),
            result
        )
    }

    @Test
    fun `load returns error when api throws HttpException`() = runTest {
        val exception = HttpException(
            Response.error<Any>(
                500,
                "".toResponseBody(null)
            )
        )

        coEvery {
            api.getCharacters(1)
        } throws exception

        val pagingSource = CharacterPagingSource(api, filters)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Error<Int, CharacterDto>(exception),
            result
        )
    }

    @Test
    fun `load returns error when api throws generic exception`() = runTest {
        val exception = RuntimeException("Something went wrong")

        coEvery {
            api.getCharacters(1)
        } throws exception

        val pagingSource = CharacterPagingSource(api, filters)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Error<Int, CharacterDto>(exception),
            result
        )
    }

    @Test
    fun `load rethrows cancellation exception`() = runTest {
        val exception = CancellationException("Test cancellation")

        coEvery {
            api.getCharacters(1)
        } throws exception

        val pagingSource = CharacterPagingSource(api, filters)

        val thrown = Assert.assertThrows(CancellationException::class.java) {
            runBlocking {
                pagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 2,
                        placeholdersEnabled = false
                    )
                )
            }
        }

        assertEquals(exception, thrown)
    }

    @Test
    fun `getRefreshKey returns next page after previous key`() {
        val pagingSource = CharacterPagingSource(api, filters)

        val state = PagingState(
            pages = listOf(
                PagingSource.LoadResult.Page(
                    data = characters,
                    prevKey = 1,
                    nextKey = 3
                )
            ),
            anchorPosition = 0,
            config = PagingConfig(pageSize = 2),
            leadingPlaceholderCount = 0
        )

        val result = pagingSource.getRefreshKey(state)

        assertEquals(2, result)
    }
}