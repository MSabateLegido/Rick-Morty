package com.marc.rickmorty.features.episode.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.marc.rickmorty.R
import com.marc.rickmorty.core.ui.theme.Outfit
import com.marc.rickmorty.core.ui.theme.spacing

@Composable
fun EpisodesHeader(
    episodeCount: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.md),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.episodes_header_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = Outfit
        )

        Text(
            text = buildAnnotatedString {
                append("${stringResource(R.string.episodes_header_appears_in)} ")

                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(episodeCount.toString())
                }

                append(" ${stringResource(R.string.episodes_header_episodes)}")
            },
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontFamily = Outfit
        )
    }
}