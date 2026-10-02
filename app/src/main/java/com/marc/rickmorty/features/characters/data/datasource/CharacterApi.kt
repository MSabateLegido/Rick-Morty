package com.marc.rickmorty.features.characters.data.datasource

import com.marc.rickmorty.features.characters.data.model.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int? = null
    ): CharacterResponseDto
}