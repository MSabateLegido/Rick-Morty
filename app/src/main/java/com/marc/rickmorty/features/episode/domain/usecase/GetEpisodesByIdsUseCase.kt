package com.marc.rickmorty.features.episode.domain.usecase

import com.marc.rickmorty.features.episode.domain.repository.EpisodeRepository
import javax.inject.Inject

class GetEpisodesByIdsUseCase @Inject constructor(
    private val episodeRepository: EpisodeRepository
) {

    suspend operator fun invoke(ids: List<Int>) =
        episodeRepository.getEpisodesByIds(ids)
}