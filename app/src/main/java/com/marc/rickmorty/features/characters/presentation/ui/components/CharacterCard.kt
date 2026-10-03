package com.marc.rickmorty.features.characters.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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