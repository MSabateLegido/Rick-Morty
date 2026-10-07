package com.marc.rickmorty.features.characters.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marc.rickmorty.features.characters.domain.usecase.GetCharacterWithEpisodesUseCase
import com.marc.rickmorty.features.characters.presentation.detail.ui.CharacterDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterWithEpisodesUseCase: GetCharacterWithEpisodesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun loadCharacter(characterId: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            try {
                val characterWithEpisode = getCharacterWithEpisodesUseCase(characterId)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        characterWithEpisode = characterWithEpisode
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message,
                        characterWithEpisode = null
                    )
                }
            }
        }
    }

}