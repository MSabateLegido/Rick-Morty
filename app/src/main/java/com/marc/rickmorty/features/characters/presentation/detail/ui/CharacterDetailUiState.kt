package com.marc.rickmorty.features.characters.presentation.detail.ui

import com.marc.rickmorty.features.characters.domain.model.CharacterWithEpisode

data class CharacterDetailUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val characterWithEpisode: CharacterWithEpisode? = null
)