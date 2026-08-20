@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.almarpa.kmmtemplateapp.presentation.ui.navigation.navdisplays

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.navigation3.ui.NavDisplay
import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.core.presentation.animations.ProvideAnimatedVisibilityScope
import com.almarpa.kmmtemplateapp.core.presentation.animations.ProvideSharedTransitionScope
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.NavigationActions
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.extensions.sharedPopTransitionSpec
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.extensions.sharedPredictivePopTransitionSpec
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.extensions.sharedTransitionSpec
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.routes.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonDetailsScreen
import com.almarpa.kmmtemplateapp.presentation.ui.screens.settings.SettingsScreen
import com.almarpa.kmmtemplateapp.presentation.ui.screens.splash.SplashScreen
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonDetailsViewModel
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavDisplay(
    modifier: Modifier = Modifier,
    backStack: List<NavKey>,
    drawerState: DrawerState,
    navigationActions: NavigationActions,
) {
    SharedTransitionLayout {
        ProvideSharedTransitionScope {
            val entryDecorators = listOf<NavEntryDecorator<NavKey>>(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
                NavEntryDecorator { entry ->
                    LocalNavAnimatedContentScope.current.ProvideAnimatedVisibilityScope {
                        entry.Content()
                    }
                }
            )
            NavDisplay(
                backStack = backStack,
                modifier = modifier,
                entryDecorators = entryDecorators,
                onBack = { navigationActions.navigateBack() },
                transitionSpec = { sharedTransitionSpec() },
                popTransitionSpec = { sharedPopTransitionSpec() },
                predictivePopTransitionSpec = { edge -> sharedPredictivePopTransitionSpec(edge) },
                entryProvider = { route ->
                    NavEntry(
                        key = route,
                    ) { targetRoute ->
                        when (targetRoute) {
                            Routes.Splash -> {
                                SplashScreen { navigationActions.navigateToHome() }
                            }

                            Routes.Main.Home -> {
                                HomeNavDisplay(
                                    drawerState = drawerState,
                                    onNavigateToDetail = { pokemon ->
                                        navigationActions.navigateToDetail(pokemon)
                                    },
                                )
                            }

                            is Routes.Main.Detail -> {
                                val pokemon = targetRoute.pokemon
                                val pokemonDetailsViewModel =
                                    koinViewModel<PokemonDetailsViewModel>()
                                val pokemonDetailsUiState by pokemonDetailsViewModel.uiState.collectAsStateWithLifecycle()

                                PokemonDetailsScreen(
                                    pokemon = pokemon,
                                    pokemonDetailsUiState = pokemonDetailsUiState,
                                    onFetchDetails = { pokemonDetailsViewModel.loadDetails(pokemon.id) },
                                    onAddTeamMember = { pokemon, added ->
                                        pokemonDetailsViewModel.addPokemonToTeam(pokemon, added)
                                    },
                                    onBackPressed = { navigationActions.navigateBack() },
                                )
                            }

                            Routes.Main.Settings -> {
                                val settingsViewModel = koinViewModel<SettingsViewModel>()
                                val settingsUiState by settingsViewModel.uiState.collectAsStateWithLifecycle()
                                SettingsScreen(
                                    uiState = settingsUiState,
                                    onLanguageChange = { settingsViewModel.setAppLocale(it) },
                                    onThemeChange = { isChecked ->
                                        settingsViewModel.setAppTheme(
                                            if (isChecked) AppThemeEnum.DARK else AppThemeEnum.LIGHT
                                        )
                                    },
                                    onBackPressed = { navigationActions.navigateBack() },
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}
