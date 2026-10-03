package com.marc.rickmorty.features.characters.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.marc.rickmorty.R
import com.marc.rickmorty.core.ui.components.LoadingScreen
import com.marc.rickmorty.core.ui.theme.spacing
import com.marc.rickmorty.features.characters.domain.model.Character

@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            var isLoading by remember { mutableStateOf(false) }
            var hasError by remember { mutableStateOf(false) }

            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                onState = { state ->
                    isLoading = state is AsyncImagePainter.State.Loading
                    hasError = state is AsyncImagePainter.State.Error
                }
            )

            if (isLoading ) {
                LoadingScreen(
                    modifier = Modifier.
                        fillMaxSize(),
                    size = MaterialTheme.spacing.lg
                )
            }

            if (hasError) {
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

            Text(
                text = character.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(MaterialTheme.spacing.md)
                    .background(
                        color = Color.Black.copy(alpha = 0.45f),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(
                        horizontal = MaterialTheme.spacing.sm,
                        vertical = MaterialTheme.spacing.sm
                    ),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}