package com.marc.rickmorty.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.marc.rickmorty.R
import com.marc.rickmorty.core.ui.theme.spacing

@Composable
fun NoResultsScreen(
    modifier: Modifier = Modifier,
    onReset: () -> Unit
) {
    Column(
        modifier = modifier.padding(MaterialTheme.spacing.xl),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_no_results),
            contentDescription = null,
            modifier = Modifier.size(MaterialTheme.spacing.huge),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.lg))

        Text(
            text = stringResource(R.string.no_results_screen_title),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

        Text(
            text = stringResource(R.string.no_results_screen_description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xl))

        Button(
            onClick = onReset
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_reset_filters),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.sm))

            Text(stringResource(R.string.character_filter_screen_reset_filters_button))
        }
    }
}