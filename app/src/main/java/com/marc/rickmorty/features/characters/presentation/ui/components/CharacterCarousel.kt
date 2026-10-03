package com.marc.rickmorty.features.characters.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marc.rickmorty.features.characters.domain.model.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCarousel(
    characters: List<Character>
) {
    val carouselState = rememberCarouselState {
        characters.size
    }

    HorizontalCenteredHeroCarousel(
        state = carouselState,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { index ->
        CharacterCard(
            character = characters[index],
            modifier = Modifier.maskClip(
                shape = MaterialTheme.shapes.large
            )
        )
    }
}