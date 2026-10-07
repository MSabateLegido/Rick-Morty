package com.marc.rickmorty.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.marc.rickmorty.features.characters.presentation.characters.ui.CharacterScreen
import com.marc.rickmorty.features.characters.presentation.detail.ui.CharacterDetailScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = AppRoute.Characters,
            modifier = modifier
        ) {
            composable<AppRoute.Characters> {
                CharacterScreen(
                    onCharacterClick = { character ->
                        navController.navigate(
                            AppRoute.CharacterDetail(character.id)
                        )
                    },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this
                )
            }

            composable<AppRoute.CharacterDetail> { backStackEntry ->

                val route = backStackEntry.toRoute<AppRoute.CharacterDetail>()

                CharacterDetailScreen(
                    characterId = route.characterId,
                    onBack = { navController.popBackStack() },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this
                )
            }
        }
    }
}