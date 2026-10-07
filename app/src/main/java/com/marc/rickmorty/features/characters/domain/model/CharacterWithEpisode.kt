package com.marc.rickmorty.features.characters.domain.model

import com.marc.rickmorty.features.episode.domain.model.Episode

data class CharacterWithEpisode(
    val character: Character,
    val episodes: List<Episode>
)