package com.marc.rickmorty.features.characters.presentation.detail.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.marc.rickmorty.core.ui.theme.spacing
import com.marc.rickmorty.features.characters.domain.model.CharacterWithEpisode
import com.marc.rickmorty.features.characters.presentation.characters.ui.components.CharacterInfo
import com.marc.rickmorty.features.episode.presentation.ui.EpisodesList

@Composable
fun CharacterDetail(
    characterWithEpisode: CharacterWithEpisode,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = with(sharedTransitionScope) {
                Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(
                            key = "character-image-${characterWithEpisode.character.id}"
                        ),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(
                                durationMillis = 650,
                                easing = EaseOutExpo
                            )
                        }
                    )
            }
        ) {
            AsyncImage(
                model = characterWithEpisode.character.image,
                contentDescription = characterWithEpisode.character.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            CharacterInfo(characterWithEpisode.character)
        }


        CharacterInfoGrid(characterWithEpisode.character)

        EpisodesList(
            episodes = characterWithEpisode.episodes,
            modifier = Modifier.padding(MaterialTheme.spacing.md)
        )
    }
}