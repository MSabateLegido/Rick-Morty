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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.marc.rickmorty.R
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
        color = colorResource(R.color.character_filters_container_color)
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
                label = stringResource(R.string.character_filters_name_label),
                value = filters.name.orEmpty(),
                placeholder = stringResource(R.string.character_filters_name_placeholder),
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
                label = stringResource(R.string.character_filters_species_label),
                value = filters.species.orEmpty(),
                placeholder = stringResource(R.string.character_filters_species_placeholder),
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
                label = stringResource(R.string.character_type_label),
                value = filters.type.orEmpty(),
                placeholder = stringResource(R.string.character_filters_type_placeholder),
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
                    label = stringResource(R.string.character_status_label),
                    selected = filters.status,
                    options = listOf(
                        null to stringResource(R.string.character_filters_dropdown_all_tag),
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
                    label = stringResource(R.string.character_gender_label),
                    selected = filters.gender,
                    options = listOf(
                        null to stringResource(R.string.character_filters_dropdown_all_tag),
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
                            text = stringResource(R.string.character_filter_screen_reset_filters_button),
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
                        text = stringResource(R.string.character_filter_screen_apply_filters_button),
                        fontFamily = Outfit,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}