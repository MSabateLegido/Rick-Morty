package com.marc.rickmorty.features.characters.presentation.detail.ui

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marc.rickmorty.core.ui.components.ErrorScreen
import com.marc.rickmorty.core.ui.components.LoadingScreen
import com.marc.rickmorty.core.ui.theme.spacing
import com.marc.rickmorty.features.characters.presentation.detail.ui.components.CharacterDetail
import com.marc.rickmorty.features.characters.presentation.detail.viewmodel.CharacterDetailViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(characterId) {
        viewModel.loadCharacter(characterId)
    }

    val characterWithEpisode = uiState.characterWithEpisode
    when  {
        uiState.isLoading -> {
            LoadingScreen(
                modifier = Modifier
                    .fillMaxSize(),
                size = MaterialTheme.spacing.huge
            )
        }

        uiState.error != null -> {
            ErrorScreen(
                modifier = Modifier.fillMaxSize(),
                onRetry = { viewModel.loadCharacter(characterId) }
            )
        }

        characterWithEpisode != null -> {
            CharacterDetail(
                characterWithEpisode = characterWithEpisode,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }
}