package com.marc.rickmorty.features.characters.presentation.detail.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.marc.rickmorty.R
import com.marc.rickmorty.core.ui.theme.spacing
import com.marc.rickmorty.features.characters.domain.model.Character
import com.marc.rickmorty.features.characters.domain.model.Gender
import com.marc.rickmorty.features.characters.domain.model.Status


@Composable
fun CharacterInfoGrid(
    character: Character
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.md),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)
        ) {
            CharacterInfoCard(
                modifier = Modifier.weight(1f),
                icon = painterResource(R.drawable.ic_status),
                label = stringResource(R.string.character_status_label),
                value = character.status.value,
                containerColor = when(character.status) {
                    Status.ALIVE -> colorResource((R.color.character_info_alive_container_color))
                    Status.DEAD -> colorResource((R.color.character_info_dead_container_color))
                    Status.UNKNOWN -> colorResource((R.color.character_info_unknown_status_container_color))
                },
                iconColor = when(character.status) {
                    Status.ALIVE -> colorResource((R.color.character_info_alive_icon_color))
                    Status.DEAD -> colorResource((R.color.character_info_dead_icon_color))
                    Status.UNKNOWN -> colorResource((R.color.character_info_unknown_status_icon_color))
                },
                iconSize = MaterialTheme.spacing.lg
            )

            CharacterInfoCard(
                modifier = Modifier.weight(1f),
                icon = when (character.gender) {
                    Gender.MALE -> painterResource(R.drawable.ic_gender_male)
                    Gender.FEMALE -> painterResource(R.drawable.ic_gender_female)
                    Gender.GENDERLESS -> painterResource(R.drawable.ic_gender_unknown)
                    Gender.UNKNOWN -> painterResource(R.drawable.ic_gender_unknown)
                },
                label = stringResource(R.string.character_gender_label),
                value = character.gender.value,
                containerColor =  when (character.gender) {
                    Gender.MALE -> colorResource((R.color.character_info_male_container_color))
                    Gender.FEMALE -> colorResource((R.color.character_info_female_container_color))
                    Gender.GENDERLESS -> colorResource((R.color.character_info_genderless_container_color))
                    Gender.UNKNOWN -> colorResource((R.color.character_info_unknown_gender_container_color))
                },
                iconColor =  when (character.gender) {
                    Gender.MALE -> colorResource((R.color.character_info_male_icon_color))
                    Gender.FEMALE -> colorResource((R.color.character_info_female_icon_color))
                    Gender.GENDERLESS -> colorResource((R.color.character_info_genderless_icon_color))
                    Gender.UNKNOWN -> colorResource((R.color.character_info_unknown_gender_icon_color))
                },
                iconSize = MaterialTheme.spacing.lg
            )
        }

        if (character.type.isNotEmpty()) {
            CharacterInfoCard(
                modifier = Modifier.fillMaxWidth(),
                icon = painterResource(R.drawable.ic_type),
                label = stringResource(R.string.character_type_label),
                value = character.type,
                containerColor = colorResource((R.color.character_info_type_container_color)),
                iconColor = colorResource((R.color.character_info_type_icon_color)),
                iconSize = MaterialTheme.spacing.lg
            )
        }
    }
}