package com.marc.rickmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marc.rickmorty.features.characters.presentation.ui.CharacterScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Characters
    ) {
        composable<AppRoute.Characters> {
            CharacterScreen()
        }
    }
}