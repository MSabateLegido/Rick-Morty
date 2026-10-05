package com.marc.rickmorty.features.characters.presentation.characters.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.marc.rickmorty.core.ui.components.ErrorScreen
import com.marc.rickmorty.core.ui.components.LoadingScreen
import com.marc.rickmorty.core.ui.theme.spacing
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.presentation.characters.ui.components.CharacterCarousel
import com.marc.rickmorty.features.characters.presentation.characters.ui.components.CharacterTopAppBar
import com.marc.rickmorty.features.characters.presentation.characters.ui.components.filters.CharacterFilters
import com.marc.rickmorty.features.characters.presentation.characters.viewmodel.CharacterViewModel


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CharacterScreen(
    onCharacterClick: (Character) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: CharacterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val characters = viewModel.characters.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CharacterTopAppBar(
                onFiltersClick = viewModel::toggleFilters
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedVisibility(
                visible = uiState.filtersVisible
            ) {
                CharacterFilters(
                    filters = uiState.editingFilters,
                    onFiltersChange = viewModel::updateEditingFilters,
                    onApply = viewModel::applyFilters
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                when (characters.loadState.refresh) {
                    is LoadState.Loading -> {
                        LoadingScreen(
                            modifier = Modifier.fillMaxSize(),
                            size = MaterialTheme.spacing.xxl
                        )
                    }

                    is LoadState.Error -> {
                        ErrorScreen(
                            modifier = Modifier.fillMaxSize(),
                            onRetry = characters::retry
                        )
                    }

                    is LoadState.NotLoading -> {
                        CharacterCarousel(
                            characters = characters,
                            onCharacterClick = onCharacterClick,
                            sharedTransitionScope = sharedTransitionScope,
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                    }
                }
            }
        }
    }
}