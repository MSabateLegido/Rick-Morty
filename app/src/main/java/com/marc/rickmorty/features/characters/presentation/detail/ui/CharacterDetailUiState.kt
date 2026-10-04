package com.marc.rickmorty.features.characters.presentation.detail.ui

import com.marc.rickmorty.features.characters.domain.model.Character

data class CharacterDetailUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val character: Character? = null
)