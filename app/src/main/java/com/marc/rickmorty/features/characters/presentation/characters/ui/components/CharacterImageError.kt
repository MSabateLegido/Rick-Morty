package com.marc.rickmorty.features.characters.presentation.characters.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.marc.rickmorty.R
import com.marc.rickmorty.core.ui.theme.spacing

@Composable
fun CharacterImageError() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_image_placeholder),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.xxl),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}