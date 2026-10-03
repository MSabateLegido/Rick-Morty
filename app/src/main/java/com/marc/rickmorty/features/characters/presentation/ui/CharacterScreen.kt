package com.marc.rickmorty.features.characters.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marc.rickmorty.features.characters.presentation.ui.components.CharacterCarousel
import com.marc.rickmorty.features.characters.presentation.viewmodel.CharacterViewModel


@Composable
fun CharacterScreen(
    viewModel: CharacterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadCharacters()
    }

    CharacterCarousel(
        uiState.characters
    )
}