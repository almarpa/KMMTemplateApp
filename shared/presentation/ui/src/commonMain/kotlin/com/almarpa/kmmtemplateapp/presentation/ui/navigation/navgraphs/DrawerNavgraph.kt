package com.almarpa.kmmtemplateapp.presentation.ui.navigation.navgraphs

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.NavigationActions
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.screens.settings.SettingsScreen
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.drawerNavGraph(navigationActions: NavigationActions) {
    composable<Routes.Settings> {
        val settingsViewModel = koinViewModel<SettingsViewModel>()
        val settingsUiState by settingsViewModel.uiState.collectAsStateWithLifecycle()
        SettingsScreen(
            uiState = settingsUiState,
            onLanguageChange = { settingsViewModel.setAppLocale(it) },
            onThemeChange = { isChecked -> settingsViewModel.setAppTheme(isChecked) },
            onBackPressed = { navigationActions.navigateBack() },
        )
    }
}
