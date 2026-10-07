package com.marc.rickmorty.features.characters.presentation.characters.ui.components.filters

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.marc.rickmorty.R
import com.marc.rickmorty.core.ui.theme.Outfit
import com.marc.rickmorty.core.ui.theme.spacing


@Composable
fun CharacterFilterTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontFamily = Outfit,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(
                bottom = MaterialTheme.spacing.xs,
                end = MaterialTheme.spacing.sm
            )
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.xxl),
            placeholder = {
                Text(
                    text = placeholder,
                    fontFamily = Outfit,
                    fontSize = 16.sp
                )
            },
            textStyle = LocalTextStyle.current.copy(
                fontFamily = Outfit,
                fontSize = 16.sp
            ),
            singleLine = true,
            shape = RoundedCornerShape(MaterialTheme.spacing.md),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.character_text_field_container_color),
                unfocusedContainerColor = colorResource(R.color.character_text_field_container_color),
                disabledContainerColor = colorResource(R.color.character_text_field_container_color),

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,

                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}