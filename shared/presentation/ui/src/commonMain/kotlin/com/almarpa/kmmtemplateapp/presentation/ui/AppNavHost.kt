@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.almarpa.kmmtemplateapp.presentation.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.navigation3.ui.NavDisplay
import com.almarpa.kmmtemplateapp.core.presentation.animations.ProvideAnimatedVisibilityScope
import com.almarpa.kmmtemplateapp.core.presentation.animations.ProvideSharedTransitionScope
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.NavigationActions
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.graphs.HomeNavigation
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.routes.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonDetailsScreen
import com.almarpa.kmmtemplateapp.presentation.ui.screens.settings.SettingsScreen
import com.almarpa.kmmtemplateapp.presentation.ui.screens.splash.SplashScreen
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonDetailsViewModel
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    backStack: List<NavKey>,
    drawerState: DrawerState,
    navigationActions: NavigationActions,
) {
    SharedTransitionLayout {
        ProvideSharedTransitionScope {
            val entryDecorators = listOf<NavEntryDecorator<NavKey>>(
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
                entryProvider = { route ->
                    NavEntry(
                        key = route,
                    ) { targetRoute ->
                        when (targetRoute) {
                            Routes.Splash -> {
                                SplashScreen { navigationActions.navigateToHome() }
                            }

                            Routes.Main.Home -> {
                                HomeNavigation(
                                    drawerState = drawerState,
                                    onNavigateToDetail = { pokemon ->
                                        navigationActions.navigateToDetail(pokemon)
                                    },
                                    navigationActions = navigationActions
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
                                        settingsViewModel.setAppTheme(isChecked)
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
