@file:OptIn(ExperimentalSharedTransitionApi::class, ExperimentalSharedTransitionApi::class)

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
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SettingsViewModel
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.TeamUiState
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.TeamViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.bottomAppBarNavGraph(
    sharedTransitionScope: SharedTransitionScope,
    drawerState: DrawerState,
    currentRoute: Routes,
    navigationActions: NavigationActions,
) {
    composable<Routes.PokemonList> {
        val pokemonListViewModel = koinViewModel<PokemonListViewModel>()
        val pokemonListUiState by pokemonListViewModel.pokemonListUiState.collectAsStateWithLifecycle()
        val searchUiState: SearchUiState by pokemonListViewModel.searchUiState.collectAsStateWithLifecycle()

        sharedTransitionScope.PokemonListScreen(
            animatedVisibilityScope = this,
            drawerState = drawerState,
            currentRoute = currentRoute,
            navigationActions = navigationActions,
            searchUiState = searchUiState,
            pokemonListUiState = pokemonListUiState,
            onReload = { /*paginatedPokemonList.refresh()*/ },
            onSearch = { text -> pokemonListViewModel.onPokemonSearch(text) },
            onDismissSearch = { pokemonListViewModel.removeCurrentSearch() },
        )
    }
    composable<Routes.Team> {
        val teamViewModel = koinViewModel<TeamViewModel>()
        val teamUiState: TeamUiState by teamViewModel.uiState.collectAsStateWithLifecycle()

        TeamScreen(
            drawerState = drawerState,
            currentRoute = currentRoute,
            navigationActions = navigationActions,
            uiState = teamUiState,
            onRetry = { teamViewModel.getTeamList() },
            onSave = { pokemon -> teamViewModel.createPokemonMemberAndReloadTeam(pokemon) }
        )
    }
    composable<Pokemon> { navBackStackEntry ->
        val pokemonDetailsViewModel = koinViewModel<PokemonDetailsViewModel>()
        val teamViewModel = koinViewModel<TeamViewModel>()
        val settingsViewModel = koinViewModel<SettingsViewModel>()

        val pokemonId = navBackStackEntry.toRoute<Pokemon>().id
        val pokemonDetailsUiState by pokemonDetailsViewModel.detailsUiState.collectAsStateWithLifecycle()
        // TODO: unify viemodels
        val userData by settingsViewModel.userData.collectAsStateWithLifecycle()

        sharedTransitionScope.PokemonDetailsScreen(
            animatedVisibilityScope = this,
            pokemon = navBackStackEntry.toRoute<Pokemon>(),
            pokemonDetailsUiState = pokemonDetailsUiState,
            userAppTheme = userData.theme,
            onFetchDetails = { pokemonDetailsViewModel.getPokemonDetails(pokemonId) },
            onAddTeamMember = { pokemon, added -> teamViewModel.addPokemonToTeam(pokemon, added) },
            onBackPressed = { navigationActions.navigateBack() },
        )
    }
}