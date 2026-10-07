package com.marc.rickmorty.features.characters.presentation.detail.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.marc.rickmorty.R
import com.marc.rickmorty.core.common.ResourceState
import com.marc.rickmorty.core.ui.components.ErrorScreen
import com.marc.rickmorty.core.ui.components.LoadingScreen
import com.marc.rickmorty.core.ui.theme.spacing
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.presentation.characters.ui.components.CharacterInfo
import com.marc.rickmorty.features.episode.domain.model.Episode
import com.marc.rickmorty.features.episode.presentation.ui.EpisodeItem
import com.marc.rickmorty.features.episode.presentation.ui.EpisodesHeader

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CharacterDetail(
    character: Character,
    episodes: ResourceState<List<Episode>>,
    onBack: () -> Unit,
    onRetryEpisodes: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Box(
                modifier = with(sharedTransitionScope) {
                    Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(
                                key = "character-image-${character.id}"
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
                var isBackPressed by remember { mutableStateOf(false) }

                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = {
                        if (!isBackPressed) {
                            isBackPressed = true
                            onBack()
                        }
                    },
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.sm)
                        .align(Alignment.TopStart)
                        .background(
                            color = Color.Black.copy(alpha = 0.2f),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = null
                    )
                }

                CharacterInfo(character)
            }
        }

        item {
            CharacterInfoGrid(character)
        }

        when (episodes) {
            is ResourceState.Loading -> {
                item {
                    LoadingScreen(
                        modifier = Modifier.fillMaxWidth(),
                        size = MaterialTheme.spacing.huge
                    )
                }
            }

            is ResourceState.Error -> {
                item {
                    ErrorScreen(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(R.string.episode_error_screen_title),
                        description = stringResource(R.string.episode_error_screen_description),
                        onRetry = onRetryEpisodes
                    )
                }
            }

            is ResourceState.Success -> {
                item {
                    EpisodesHeader(
                        episodeCount = episodes.data.size
                    )
                }

                items(
                    items = episodes.data,
                    key = { episode -> episode.id }
                ) { episode ->
                    EpisodeItem(
                        episode = episode,
                        modifier = Modifier.padding(
                            horizontal = MaterialTheme.spacing.md,
                            vertical = MaterialTheme.spacing.xs
                        )
                    )
                }
            }
        }
    }
}