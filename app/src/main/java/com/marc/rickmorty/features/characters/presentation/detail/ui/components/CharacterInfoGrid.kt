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
import androidx.compose.ui.res.painterResource
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
                label = "Status",
                value = character.status.value,
                containerColor = when(character.status) {
                    Status.ALIVE -> Color(0xFFEAF8F0)
                    Status.DEAD -> Color(0xFFFCEBEC)
                    Status.UNKNOWN -> Color(0xFFF1F2F5)
                },
                iconColor = when(character.status) {
                    Status.ALIVE -> Color(0xFF35B86B)
                    Status.DEAD -> Color(0xFFD94A59)
                    Status.UNKNOWN -> Color(0xFF7D8491)
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
                label = "Gender",
                value = character.gender.value,
                containerColor =  when (character.gender) {
                    Gender.MALE -> Color(0xFFEAF2FF)
                    Gender.FEMALE -> Color(0xFFF5EEFF)
                    Gender.GENDERLESS -> Color(0xFFE8F8F7)
                    Gender.UNKNOWN -> Color(0xFFF1F2F5)
                },
                iconColor =  when (character.gender) {
                    Gender.MALE -> Color(0xFF4A82E8)
                    Gender.FEMALE -> Color(0xFF8A5DE8)
                    Gender.GENDERLESS -> Color(0xFF35A9A0)
                    Gender.UNKNOWN -> Color(0xFF7D8491)
                },
                iconSize = MaterialTheme.spacing.lg
            )
        }

        if (character.type.isNotEmpty()) {
            CharacterInfoCard(
                modifier = Modifier.fillMaxWidth(),
                icon = painterResource(R.drawable.ic_type),
                label = "Type",
                value = character.type,
                containerColor = Color(0xFFF4F5FA),
                iconColor = Color(0xFF7D879C),
                iconSize = MaterialTheme.spacing.lg
            )
        }
    }
}