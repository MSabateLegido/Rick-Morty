package com.marc.rickmorty.features.characters.presentation.characters.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.marc.rickmorty.core.ui.theme.Outfit
import com.marc.rickmorty.core.ui.theme.spacing
import com.marc.rickmorty.features.characters.domain.model.Character


@Composable
fun BoxScope.CharacterInfo(
    character: Character
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .align(Alignment.BottomCenter)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black.copy(alpha = 0.15f),
                        Color.Black.copy(alpha = 0.85f)
                    )
                )
            )
    )

    Column(
        modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(MaterialTheme.spacing.lg)
    ) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = Outfit
        )

        Text(
            text = character.species,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.85f)
        )
    }
}