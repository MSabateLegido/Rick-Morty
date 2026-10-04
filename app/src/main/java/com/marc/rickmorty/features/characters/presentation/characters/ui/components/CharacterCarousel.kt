package com.marc.rickmorty.features.characters.presentation.characters.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.marc.rickmorty.features.characters.domain.model.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCarousel(
    characters: LazyPagingItems<Character>
) {
    val carouselState = rememberCarouselState {
        characters.itemCount
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        HorizontalCenteredHeroCarousel(
            state = carouselState,
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp),
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { index ->
            characters[index]?.let { character ->
                CharacterCard(
                    character = character,
                    modifier = Modifier.maskClip(
                        shape = MaterialTheme.shapes.large
                    )
                )
            }
        }
    }
}