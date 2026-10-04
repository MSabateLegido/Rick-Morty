package com.marc.rickmorty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marc.rickmorty.features.characters.presentation.characters.ui.CharacterScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Characters,
        modifier = modifier
    ) {
        composable<AppRoute.Characters> {
            CharacterScreen()
        }
    }
}