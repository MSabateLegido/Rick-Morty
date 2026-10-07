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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marc.rickmorty.R
import com.marc.rickmorty.core.common.ResourceState
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
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(characterId) {
        viewModel.load(characterId)
    }

    when  (val characterState = uiState.character) {
        is ResourceState.Loading -> {
            LoadingScreen(
                modifier = Modifier
                    .fillMaxSize(),
                size = MaterialTheme.spacing.huge
            )
        }
        is ResourceState.Error -> {
            ErrorScreen(
                modifier = Modifier.fillMaxSize(),
                description = stringResource(R.string.character_detail_error_screen_description),
                onRetry = { viewModel.load(characterId) }
            )
        }
        is ResourceState.Success -> {
            CharacterDetail(
                character = characterState.data,
                episodes = uiState.episodes,
                onBack = onBack,
                onRetryEpisodes = viewModel::retryEpisodes,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }

    }
}