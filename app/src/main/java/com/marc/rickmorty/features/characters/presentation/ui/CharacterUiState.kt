package com.marc.rickmorty.features.characters.presentation.ui

import com.marc.rickmorty.features.characters.domain.model.Character

data class CharacterUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null
)