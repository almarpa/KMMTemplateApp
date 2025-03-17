@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.almarpa.kmmtemplateapp.presentation.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.NavigationActions
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.navgraphs.bottomAppBarNavGraph
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.navgraphs.drawerNavGraph
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.navgraphs.splashNavGraph

@Composable
fun TemplateNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Routes,
    drawerState: DrawerState, navigationActions: NavigationActions,
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
        ) {
            splashNavGraph(navigationActions)
            bottomAppBarNavGraph(
                sharedTransitionScope = this@SharedTransitionLayout,
                drawerState = drawerState,
                navigationActions = navigationActions
            )
            drawerNavGraph(navigationActions)
        }
    }
}
