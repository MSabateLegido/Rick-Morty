package com.marc.rickmorty.features.characters.presentation.characters.ui.components.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import com.marc.rickmorty.core.ui.theme.Outfit
import com.marc.rickmorty.core.ui.theme.spacing
import com.marc.rickmorty.features.characters.domain.model.CharacterFilters
import com.marc.rickmorty.features.characters.domain.model.Gender
import com.marc.rickmorty.features.characters.domain.model.Status


@Composable
fun CharacterFilters(
    filters: CharacterFilters,
    onFiltersChange: (CharacterFilters) -> Unit,
    onApply: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.lg),
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.lg),
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
                        Status.ALIVE to "Alive",
                        Status.DEAD to "Dead",
                        Status.UNKNOWN to "Unknown"
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
                        Gender.MALE to "Male",
                        Gender.FEMALE to "Female",
                        Gender.UNKNOWN to "Unknown"
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
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onApply
                ) {
                    Text(
                        text = "Apply filters",
                        fontFamily = Outfit
                    )
                }
            }
        }
    }
}