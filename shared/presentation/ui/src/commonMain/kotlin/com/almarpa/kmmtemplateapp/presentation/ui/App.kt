package com.almarpa.kmmtemplateapp.presentation.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.NavigationActions
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.drawer.Drawer
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.navdisplays.AppNavDisplay
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.routes.Routes
import kotlinx.coroutines.launch

@Composable
fun App() {
    val backStack = remember { listOf<Routes>(Routes.Splash).toMutableStateList() }
    val navigationActions = remember(backStack) { NavigationActions(backStack) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            Drawer(
                navigateToSettings = navigationActions.navigateToSettings,
                closeDrawer = { coroutineScope.launch { drawerState.close() } },
            )
        },
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
    ) {
        AppNavDisplay(
            backStack = backStack,
            drawerState = drawerState,
            navigationActions = navigationActions,
        )
    }
}
