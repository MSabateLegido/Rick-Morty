package com.marc.rickmorty.features.characters.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.marc.rickmorty.core.ui.components.LoadingScreen
import com.marc.rickmorty.core.ui.theme.spacing

@Composable
fun CharacterLoading() {
    LoadingScreen(
        modifier = Modifier.fillMaxSize(),
        size = MaterialTheme.spacing.lg
    )
}