package com.marc.rickmorty.features.episode.data.datasource

import com.marc.rickmorty.features.episode.data.model.EpisodeDto
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {

    @GET("episode/{ids}")
    suspend fun getEpisodes(
        @Path("ids") ids: String
    ): List<EpisodeDto>
}