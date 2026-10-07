package com.marc.rickmorty.features.characters.data.datasource

import com.marc.rickmorty.features.characters.data.model.CharacterDto
import com.marc.rickmorty.features.characters.data.model.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("gender") gender: String? = null,
        @Query("species") species: String? = null,
        @Query("type") type: String? = null
    ): CharacterResponseDto

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): CharacterDto
}