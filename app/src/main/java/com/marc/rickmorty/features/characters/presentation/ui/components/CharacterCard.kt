package com.marc.rickmorty.features.characters.presentation.ui.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.marc.rickmorty.features.characters.domain.model.Character

@Composable
fun CharacterCard(
    character: Character
) {
    Card {
        Text(
            text = character.name
        )
    }
}