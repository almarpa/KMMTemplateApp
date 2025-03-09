package com.almarpa.kmmtemplateapp.presentation.ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.NavigationActions
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.screens.splash.SplashScreen

fun NavGraphBuilder.splashNavGraph(navigationActions: NavigationActions) {
    composable<Routes.Splash> {
        SplashScreen { navigationActions.navigateToPokemonList() }
    }
}
