package com.marc.rickmorty.features.characters.presentation.characters.ui.components.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marc.rickmorty.R
import com.marc.rickmorty.core.ui.theme.Outfit
import com.marc.rickmorty.core.ui.theme.spacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> CharacterFilterDropdown(
    modifier: Modifier = Modifier,
    label: String,
    selected: T?,
    options: List<Pair<T?, String>>,
    onSelected: (T?) -> Unit
) {
    var expanded by remember {
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
            fontFamily = Outfit,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(
                bottom = MaterialTheme.spacing.xs
            )
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFFAFAFC))
                    .menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                        enabled = true
                    )
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedText,
                        modifier = Modifier.weight(1f),
                        fontFamily = Outfit,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Icon(
                        painter = if (expanded) {
                            painterResource(R.drawable.ic_arrow_up)
                        } else {
                            painterResource(R.drawable.ic_arrow_down)
                        },
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
                modifier = Modifier
                    .background(Color(0xFFFAFAFC))
            ) {
                options.forEach { (value, text) ->

                    DropdownMenuItem(
                        text = {
                            Text(
                                text = text,
                                fontFamily = Outfit,
                                fontSize = 16.sp,
                                fontWeight = if (value == selected) {
                                    FontWeight.SemiBold
                                } else {
                                    FontWeight.Normal
                                }
                            )
                        },
                        onClick = {
                            onSelected(value)
                            expanded = false
                        },
                        contentPadding = PaddingValues(
                            horizontal = 16.dp
                        )
                    )
                }
            }
        }
    }
}