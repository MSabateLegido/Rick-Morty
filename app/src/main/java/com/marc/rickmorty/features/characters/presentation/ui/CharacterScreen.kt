package com.marc.rickmorty.features.characters.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.marc.rickmorty.core.ui.components.ErrorScreen
import com.marc.rickmorty.core.ui.components.LoadingScreen
import com.marc.rickmorty.core.ui.theme.spacing
import com.marc.rickmorty.features.characters.presentation.ui.components.CharacterCarousel
import com.marc.rickmorty.features.characters.presentation.viewmodel.CharacterViewModel


@Composable
fun CharacterScreen(
    viewModel: CharacterViewModel = hiltViewModel()
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize(),
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
                    characters = characters
                )
            }
        }
    }
}