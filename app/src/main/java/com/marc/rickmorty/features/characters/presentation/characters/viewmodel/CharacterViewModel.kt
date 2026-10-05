package com.marc.rickmorty.features.characters.presentation.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.domain.model.CharacterFilters
import com.marc.rickmorty.features.characters.domain.usecase.GetCharactersUseCase
import com.marc.rickmorty.features.characters.presentation.characters.ui.CharacterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CharacterViewModel @Inject constructor(
    getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterUiState())
    val uiState = _uiState.asStateFlow()

    val characters: Flow<PagingData<Character>> =
        uiState
            .map { it.appliedFilters }
            .distinctUntilChanged()
            .flatMapLatest { filters ->
                getCharactersUseCase(filters)
            }
            .cachedIn(viewModelScope)

    fun toggleFilters() {
        _uiState.update { state ->
            if (!state.filtersVisible) {
                state.copy(
                    editingFilters = state.appliedFilters,
                    filtersVisible = true
                )
            } else {
                state.copy(filtersVisible = false)
            }
        }
    }

    fun updateEditingFilters(filters: CharacterFilters) {
        _uiState.update {
            it.copy(editingFilters = filters)
        }
    }

    fun applyFilters() {
        _uiState.update {
            it.copy(
                appliedFilters = it.editingFilters,
                filtersVisible = false
            )
        }
    }
}