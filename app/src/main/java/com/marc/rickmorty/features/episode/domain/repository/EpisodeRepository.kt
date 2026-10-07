package com.marc.rickmorty.features.episode.domain.repository

import com.marc.rickmorty.features.episode.domain.model.Episode

interface EpisodeRepository {

    suspend fun getEpisodesByIds(ids: List<Int>): List<Episode>
}