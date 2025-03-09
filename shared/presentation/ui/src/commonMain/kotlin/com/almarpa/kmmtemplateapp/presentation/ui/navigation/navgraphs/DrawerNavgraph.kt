package com.almarpa.kmmtemplateapp.presentation.ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.NavigationActions
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes

fun NavGraphBuilder.drawerNavGraph(navigationActions: NavigationActions) {
    composable<Routes.Settings> {
//        val settingsViewModel: SettingsViewModel = hiltViewModel()
//        val userDataState by settingsViewModel.userData.collectAsStateWithLifecycle()
//
//        SettingsScreen(
//            userData = userDataState,
//            locales = settingsViewModel.locales,
//            onLanguageChange = { settingsViewModel.setUserAppLocale(it) },
//            onThemeChange = { isChecked -> settingsViewModel.setUserAppTheme(isChecked) },
//            onBackPressed = { navigationActions.navigateBack() },
//        )
    }
}
