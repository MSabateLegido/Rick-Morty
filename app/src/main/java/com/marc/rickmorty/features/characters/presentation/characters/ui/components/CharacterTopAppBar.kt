package com.marc.rickmorty.features.characters.presentation.characters.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.marc.rickmorty.R
import com.marc.rickmorty.core.ui.theme.Outfit
import com.marc.rickmorty.core.ui.theme.spacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterTopAppBar(
    onFiltersClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Rick & Morty",
                fontFamily = Outfit,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            )
        },
        actions = {
            FilledIconButton(
                onClick = onFiltersClick,
                shape = RoundedCornerShape(MaterialTheme.spacing.md)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_filter_clear),
                    contentDescription = "Filters"
                )
            }
        }
    )
}