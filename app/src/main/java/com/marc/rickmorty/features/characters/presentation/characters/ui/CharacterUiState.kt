package com.marc.rickmorty.features.characters.presentation.characters.ui

import com.marc.rickmorty.features.characters.domain.model.CharacterFilters


data class CharacterUiState(
    val appliedFilters: CharacterFilters = CharacterFilters(),
    val editingFilters: CharacterFilters = CharacterFilters(),
    val filtersVisible: Boolean = false
)