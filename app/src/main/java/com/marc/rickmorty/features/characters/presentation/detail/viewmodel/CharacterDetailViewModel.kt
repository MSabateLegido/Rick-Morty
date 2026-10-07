package com.marc.rickmorty.features.characters.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marc.rickmorty.core.common.ResourceState
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.domain.usecase.GetCharacterByIdUseCase
import com.marc.rickmorty.features.characters.presentation.detail.ui.CharacterDetailUiState
import com.marc.rickmorty.features.episode.domain.usecase.GetEpisodesByIdsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getEpisodesByIdsUseCase: GetEpisodesByIdsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun load(characterId: Int) {
        loadCharacter(characterId)
    }

    fun retryEpisodes() {
        val characterState = _uiState.value.character

        if (characterState !is ResourceState.Success) return

        loadEpisodes(characterState.data)
    }

    private fun loadCharacter(characterId: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(character = ResourceState.Loading)
            }

            try {
                val character = getCharacterByIdUseCase(characterId)

                _uiState.update {
                    it.copy(
                        character = ResourceState.Success(character)
                    )
                }

                loadEpisodes(character)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        character = ResourceState.Error(e.message)
                    )
                }
            }
        }
    }

    private fun loadEpisodes(character: Character) {
        val episodeIds = character.episodes
            .map { it.substringAfterLast("/").toInt() }

        viewModelScope.launch {
            _uiState.update {
                it.copy(episodes = ResourceState.Loading)
            }

            try {
                val episodes = getEpisodesByIdsUseCase(episodeIds)

                _uiState.update {
                    it.copy(
                        episodes = ResourceState.Success(episodes)
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        episodes = ResourceState.Error(e.message)
                    )
                }
            }
        }
    }
}