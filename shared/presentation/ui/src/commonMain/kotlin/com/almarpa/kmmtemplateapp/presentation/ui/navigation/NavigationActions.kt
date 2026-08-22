package com.almarpa.kmmtemplateapp.presentation.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.routes.Routes

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(private val backStack: MutableList<NavKey>) {
    val navigateToHome: () -> Unit = {
        backStack.clear()
        backStack.add(Routes.Main.Home)
    }

    val navigateToSettings: () -> Unit = {
        backStack.add(Routes.Main.Settings)
    }

    val navigateToDetail: (Pokemon) -> Unit = { pokemon ->
        backStack.add(Routes.Main.Detail(pokemon))
    }

    val navigateBack: () -> Unit = {
        if (backStack.size > 1) {
            backStack.removeLast()
        }
    }
}
