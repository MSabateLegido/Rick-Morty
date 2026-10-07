package com.marc.rickmorty.features.episode.data.repository

import com.marc.rickmorty.features.episode.data.datasource.EpisodeApi
import com.marc.rickmorty.features.episode.domain.model.Episode
import com.marc.rickmorty.features.episode.domain.repository.EpisodeRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val episodeApi: EpisodeApi
) : EpisodeRepository {

    private val episodeCache = mutableMapOf<Int, Episode>()
    private val episodeCacheMutex = Mutex()

    override suspend fun getEpisodesByIds(ids: List<Int>): List<Episode> {
        val cachedEpisodes = episodeCacheMutex.withLock {
            ids.mapNotNull { episodeCache[it] }
        }

        val cachedIds = cachedEpisodes.map { it.id }.toSet()
        val missingIds = ids.filterNot { it in cachedIds }

        val fetchedEpisodes = if (missingIds.isNotEmpty()) {
            if (missingIds.size == 1) {
                val episodeDto = episodeApi.getEpisode(missingIds.first())

                listOf(
                    Episode(
                        id = episodeDto.id,
                        name = episodeDto.name,
                        airDate = episodeDto.airDate,
                        episode = episodeDto.episode,
                        characters = episodeDto.characters,
                        url = episodeDto.url,
                        created = episodeDto.created
                    )
                )
            } else {
                val episodeIds = missingIds.joinToString(",")

                episodeApi.getEpisodes(episodeIds).map { episodeDto ->
                    Episode(
                        id = episodeDto.id,
                        name = episodeDto.name,
                        airDate = episodeDto.airDate,
                        episode = episodeDto.episode,
                        characters = episodeDto.characters,
                        url = episodeDto.url,
                        created = episodeDto.created
                    )
                }
            }.also { episodes ->
                episodeCacheMutex.withLock {
                    episodes.forEach { episode ->
                        episodeCache[episode.id] = episode
                    }
                }
            }
        } else {
            emptyList()
        }

        val episodesById = (cachedEpisodes + fetchedEpisodes)
            .associateBy { it.id }

        return ids.mapNotNull { episodesById[it] }
    }
}