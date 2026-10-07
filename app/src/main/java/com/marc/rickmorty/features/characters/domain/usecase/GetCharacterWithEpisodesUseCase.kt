package com.marc.rickmorty.features.characters.domain.usecase

import com.marc.rickmorty.features.characters.domain.model.CharacterWithEpisode
import com.marc.rickmorty.features.characters.domain.repository.CharacterRepository
import com.marc.rickmorty.features.episode.domain.repository.EpisodeRepository
import javax.inject.Inject

class GetCharacterWithEpisodesUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val episodeRepository: EpisodeRepository
) {

    suspend operator fun invoke(characterId: Int) : CharacterWithEpisode {
        val character = characterRepository.getCharacterById(characterId)

        val episodeIds = character.episodes.map {
            it.substringAfterLast("/").toInt()
        }

        val episodes = episodeRepository.getEpisodesByIds(episodeIds)

        return CharacterWithEpisode(
            character = character,
            episodes = episodes
        )
    }
}