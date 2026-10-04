package com.marc.rickmorty.features.characters.presentation.characters.ui.components

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

@Composable
fun CharacterImage(
    character: Character
) {
    var imageState by remember {
        mutableStateOf<AsyncImagePainter.State>(
            AsyncImagePainter.State.Empty
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
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