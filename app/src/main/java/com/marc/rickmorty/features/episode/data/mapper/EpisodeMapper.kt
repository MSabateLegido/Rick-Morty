package com.marc.rickmorty.features.episode.data.mapper

import com.marc.rickmorty.features.episode.data.model.EpisodeDto
import com.marc.rickmorty.features.episode.domain.model.Episode

fun EpisodeDto.toDomain(): Episode {
    return Episode(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
        characters = characters,
        url = url,
        created = created
    )
}