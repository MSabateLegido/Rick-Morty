package com.marc.rickmorty.features.characters.presentation.characters.ui.components.filters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.marc.rickmorty.R
import com.marc.rickmorty.core.ui.theme.Outfit
import com.marc.rickmorty.core.ui.theme.spacing


@Composable
fun <T> CharacterFilterDropdown(
    modifier: Modifier = Modifier,
    label: String,
    selected: T?,
    options: List<Pair<T?, String>>,
    onSelected: (T?) -> Unit
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    val selectedText = options
        .firstOrNull { it.first == selected }
        ?.second
        ?: "All"

    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(
                bottom = MaterialTheme.spacing.xs
            ),
            fontFamily = Outfit
        )

        Box {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded = true
                    },
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.spacing.md,
                            vertical = MaterialTheme.spacing.md
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = selectedText,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Icon(
                        painter = if (expanded) painterResource(R.drawable.ic_arrow_up) else painterResource(R.drawable.ic_arrow_down),
                        contentDescription = null
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { (value, text) ->
                    DropdownMenuItem(
                        text = {
                            Text(text)
                        },
                        onClick = {
                            onSelected(value)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}