package com.almarpa.kmmtemplateapp.presentation.ui.navigation.navdisplays

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.togetherWith
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.config.homeSavedStateConfig
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.routes.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.PokemonListScreen
import com.almarpa.kmmtemplateapp.presentation.ui.screens.team.TeamScreen
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonListViewModel
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SearchUiState
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.TeamUiState
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.TeamViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeNavDisplay(
    drawerState: DrawerState,
    onNavigateToDetail: (Pokemon) -> Unit,
) {
    val homeBackStack = rememberNavBackStack(
        homeSavedStateConfig,
        Routes.HomeDestination.PokemonList
    )

    val currentDestination = homeBackStack.last() as Routes.HomeDestination

    NavDisplay(
        backStack = homeBackStack,
        onBack = { if (homeBackStack.size > 1) homeBackStack.removeAt(homeBackStack.lastIndex) },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        transitionSpec = { EnterTransition.None togetherWith ExitTransition.None },
        popTransitionSpec = { EnterTransition.None togetherWith ExitTransition.None },
        entryProvider = entryProvider {
            entry<Routes.HomeDestination.PokemonList> {
                val pokemonListViewModel = koinViewModel<PokemonListViewModel>()
                val pokemonListUiState by pokemonListViewModel.uiState.collectAsStateWithLifecycle()
                val searchUiState: SearchUiState by pokemonListViewModel.searchUiState.collectAsStateWithLifecycle()

                PokemonListScreen(
                    drawerState = drawerState,
                    currentRoute = currentDestination,
                    searchUiState = searchUiState,
                    pokemonListUiState = pokemonListUiState,
                    onReload = { pokemonListViewModel.loadList() },
                    onSearch = { text -> pokemonListViewModel.onPokemonSearch(text) },
                    onDismissSearch = { pokemonListViewModel.removeCurrentSearch() },
                    onPokemonItemClick = onNavigateToDetail,
                    onBottomBarItemClick = { newRoute ->
                        if (newRoute is Routes.HomeDestination && newRoute != currentDestination) {
                            homeBackStack.add(newRoute)
                        }
                    },
                )
            }

            entry<Routes.HomeDestination.Team> {
                val teamViewModel = koinViewModel<TeamViewModel>()
                val teamUiState: TeamUiState by teamViewModel.uiState.collectAsStateWithLifecycle()

                TeamScreen(
                    drawerState = drawerState,
                    currentRoute = currentDestination,
                    uiState = teamUiState,
                    onRetry = { teamViewModel.loadData() },
                    onSave = { pokemon ->
                        teamViewModel.createPokemonMemberAndReloadTeam(pokemon)
                    },
                    onBottomBarItemClick = { newRoute ->
                        if (newRoute is Routes.HomeDestination && newRoute != currentDestination) {
                            homeBackStack.add(newRoute)
                        }
                    }
                )
            }
        }
    )
}
