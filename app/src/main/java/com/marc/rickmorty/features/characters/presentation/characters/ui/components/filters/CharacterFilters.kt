package com.marc.rickmorty.features.characters.presentation.characters.ui.components.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.marc.rickmorty.core.ui.theme.Outfit
import com.marc.rickmorty.core.ui.theme.spacing
import com.marc.rickmorty.features.characters.domain.model.CharacterFilters
import com.marc.rickmorty.features.characters.domain.model.Gender
import com.marc.rickmorty.features.characters.domain.model.Status


@Composable
fun CharacterFilters(
    filters: CharacterFilters,
    filtersApplied: Boolean,
    onFiltersChange: (CharacterFilters) -> Unit,
    onApply: () -> Unit,
    onReset: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.lg),
        shape = RoundedCornerShape(MaterialTheme.spacing.lg),
        color = Color(0xFFF1F1F6)
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.lg,
                vertical = MaterialTheme.spacing.md
            ),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md)
        ) {

            CharacterFilterTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Name",
                value = filters.name.orEmpty(),
                placeholder = "Rick, Morty...",
                onValueChange = {
                    onFiltersChange(
                        filters.copy(
                            name = it.ifBlank { null }
                        )
                    )
                }
            )

            CharacterFilterTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Species",
                value = filters.species.orEmpty(),
                placeholder = "Human, Alien...",
                onValueChange = {
                    onFiltersChange(
                        filters.copy(
                            species = it.ifBlank { null }
                        )
                    )
                }
            )

            CharacterFilterTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Type",
                value = filters.type.orEmpty(),
                placeholder = "Clone, Robot...",
                onValueChange = {
                    onFiltersChange(
                        filters.copy(
                            type = it.ifBlank { null }
                        )
                    )
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.md
                )
            ) {
                CharacterFilterDropdown(
                    modifier = Modifier.weight(1f),
                    label = "Status",
                    selected = filters.status,
                    options = listOf(
                        null to "All",
                        Status.ALIVE to Status.ALIVE.value,
                        Status.DEAD to Status.DEAD.value,
                        Status.UNKNOWN to Status.UNKNOWN.value
                    ),
                    onSelected = {
                        onFiltersChange(
                            filters.copy(status = it)
                        )
                    }
                )

                CharacterFilterDropdown(
                    modifier = Modifier.weight(1f),
                    label = "Gender",
                    selected = filters.gender,
                    options = listOf(
                        null to "All",
                        Gender.MALE to Gender.MALE.value,
                        Gender.FEMALE to Gender.FEMALE.value,
                        Gender.GENDERLESS to Gender.GENDERLESS.value,
                        Gender.UNKNOWN to Gender.UNKNOWN.value
                    ),
                    onSelected = {
                        onFiltersChange(
                            filters.copy(gender = it)
                        )
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (filtersApplied )
                    Arrangement.SpaceEvenly
                else
                    Arrangement.End,

            ) {
                if (filtersApplied) {
                    Button(
                        onClick = onReset,
                        shape = RoundedCornerShape(MaterialTheme.spacing.lg),
                        contentPadding = PaddingValues(
                            horizontal = MaterialTheme.spacing.lg,
                            vertical = MaterialTheme.spacing.sm
                        )
                    ) {
                        Text(
                            text = "Reset filters",
                            fontFamily = Outfit,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Button(
                    onClick = onApply,
                    shape = RoundedCornerShape(MaterialTheme.spacing.lg),
                    contentPadding = PaddingValues(
                        horizontal = MaterialTheme.spacing.lg,
                        vertical = MaterialTheme.spacing.sm
                    )
                ) {
                    Text(
                        text = "Apply filters",
                        fontFamily = Outfit,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}