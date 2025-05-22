package com.almarpa.kmmtemplateapp.presentation.ui.navigation.navgraphs

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.NavigationActions
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonDetailsScreen
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.PokemonListScreen
import com.almarpa.kmmtemplateapp.presentation.ui.screens.team.TeamScreen
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonDetailsViewModel
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonListViewModel
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SearchUiState
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.TeamUiState
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.TeamViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.bottomAppBarNavGraph(
    sharedTransitionScope: SharedTransitionScope,
    drawerState: DrawerState,
    navigationActions: NavigationActions,
) {
    composable<Routes.PokemonList> {
        val pokemonListViewModel = koinViewModel<PokemonListViewModel>()
        val pokemonListUiState by pokemonListViewModel.pokemonListUiState.collectAsStateWithLifecycle()
        val searchUiState: SearchUiState by pokemonListViewModel.searchUiState.collectAsStateWithLifecycle()

        sharedTransitionScope.PokemonListScreen(
            animatedVisibilityScope = this,
            drawerState = drawerState,
            currentRoute = Routes.PokemonList,
            searchUiState = searchUiState,
            pokemonListUiState = pokemonListUiState,
            onReload = { pokemonListViewModel.fetchPokemonList() },
            onSearch = { text -> pokemonListViewModel.onPokemonSearch(text) },
            onDismissSearch = { pokemonListViewModel.removeCurrentSearch() },
            onPokemonItemClick = { navigationActions.navigateToDetailNavGraph(it) },
            onBottomBarItemClick = { newRoute ->
                when (newRoute) {
                    Routes.PokemonList -> navigationActions.navigateToPokemonList()
                    Routes.Team -> navigationActions.navigateToTeamList()
                    else -> { /* Do nothing */
                    }
                }
            },
        )
    }

    composable<Pokemon> { navBackStackEntry ->
        val pokemonId = navBackStackEntry.toRoute<Pokemon>().id
        val pokemonDetailsViewModel = koinViewModel<PokemonDetailsViewModel>()
        val pokemonDetailsUiState by pokemonDetailsViewModel.detailsUiState.collectAsStateWithLifecycle()

        sharedTransitionScope.PokemonDetailsScreen(
            animatedVisibilityScope = this,
            pokemon = navBackStackEntry.toRoute<Pokemon>(),
            pokemonDetailsUiState = pokemonDetailsUiState,
            onFetchDetails = { pokemonDetailsViewModel.getPokemonDetails(pokemonId) },
            onAddTeamMember = { pokemon, added ->
                pokemonDetailsViewModel.addPokemonToTeam(pokemon, added)
            },
            onBackPressed = { navigationActions.navigateBack() },
        )
    }

    composable<Routes.Team> {
        val teamViewModel = koinViewModel<TeamViewModel>()
        val teamUiState: TeamUiState by teamViewModel.uiState.collectAsStateWithLifecycle()

        TeamScreen(
            drawerState = drawerState,
            currentRoute = Routes.Team,
            navigationActions = navigationActions,
            uiState = teamUiState,
            onRetry = { teamViewModel.fetchTeamList() },
            onSave = { pokemon -> teamViewModel.createPokemonMemberAndReloadTeam(pokemon) }
        )
    }
}