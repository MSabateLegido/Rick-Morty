package com.marc.rickmorty.features.characters.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marc.rickmorty.features.characters.data.datasource.CharacterApi
import com.marc.rickmorty.features.characters.data.model.CharacterDto
import com.marc.rickmorty.core.network.executeRetryAfter
import com.marc.rickmorty.features.characters.domain.model.CharacterFilters
import retrofit2.HttpException
import kotlin.coroutines.cancellation.CancellationException

class CharacterPagingSource(
    private val api: CharacterApi,
    private val filters: CharacterFilters
) : PagingSource<Int, CharacterDto>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): PagingSource.LoadResult<Int, CharacterDto> {

        val page = params.key ?: 1

        return try {
            val response = executeRetryAfter {
                api.getCharacters(
                    page = page,
                    name = filters.name,
                    status = filters.status?.value,
                    gender = filters.gender?.value,
                    species = filters.species,
                    type = filters.type
                )
            }

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.info.next == null) null else page + 1
            )
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
        catch (e: CancellationException) {
            throw e
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, CharacterDto>
    ): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}