package com.almarpa.kmmtemplateapp.presentation.ui.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.routes.Routes

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(private val navController: NavHostController) {
    val navigateToPokemonList: () -> Unit = {
        navController.navigate(Routes.PokemonList) {
            popUpTo(navController.graph.findStartDestination().navigatorName) {
                saveState = true
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToTeamList: () -> Unit = {
        navController.navigate(Routes.Team) {
            popUpTo(navController.graph.findStartDestination().navigatorName) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToSettings: () -> Unit = {
        navController.navigate(Routes.Settings) {
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToDetailNavGraph: (Pokemon) -> Unit = { pokemon ->
        navController.navigate(Routes.Detail(pokemon))
    }

    val navigateBack: () -> Unit = {
        if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
            navController.popBackStack()
        }
    }
}