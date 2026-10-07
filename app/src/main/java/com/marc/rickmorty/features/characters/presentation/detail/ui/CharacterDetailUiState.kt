package com.marc.rickmorty.features.characters.presentation.detail.ui

import com.marc.rickmorty.core.common.ResourceState
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.episode.domain.model.Episode

data class CharacterDetailUiState(
    val character: ResourceState<Character> = ResourceState.Loading,
    val episodes: ResourceState<List<Episode>> = ResourceState.Loading
)