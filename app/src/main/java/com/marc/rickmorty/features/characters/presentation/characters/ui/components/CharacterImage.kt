package com.marc.rickmorty.features.characters.presentation.characters.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.marc.rickmorty.features.characters.domain.model.Character

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CharacterImage(
    character: Character,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    var imageState by remember {
        mutableStateOf<AsyncImagePainter.State>(
            AsyncImagePainter.State.Empty
        )
    }

    Box(
        modifier = with(sharedTransitionScope) {
            Modifier
                .fillMaxSize()
                .sharedElement(
                    sharedContentState = rememberSharedContentState(
                        key = "character-image-${character.id}"
                    ),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(
                            durationMillis = 550,
                            easing = EaseInOutCubic
                        )
                    }
                )
        }
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            onState = { state ->
                imageState = state
            }
        )

        when (imageState) {
            is AsyncImagePainter.State.Loading -> CharacterLoading()
            is AsyncImagePainter.State.Error -> CharacterImageError()
            is AsyncImagePainter.State.Success -> CharacterInfo(character)
            else -> Unit
        }
    }
}