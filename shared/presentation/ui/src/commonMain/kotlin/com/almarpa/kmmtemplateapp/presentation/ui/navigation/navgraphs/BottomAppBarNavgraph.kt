package com.almarpa.kmmtemplateapp.presentation.ui.navigation.navgraphs

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.NavigationActions
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.PokemonListScreen
import com.almarpa.kmmtemplateapp.presentation.ui.screens.team.TeamScreen
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonListViewModel
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SearchUiState
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.TeamUiState
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.TeamViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.bottomAppBarNavGraph(
    sharedTransitionScope: SharedTransitionScope,
    drawerState: DrawerState,
    currentRoute: Routes,
    navigationActions: NavigationActions,
) {
    composable<Routes.PokemonList> {
        val pokemonListViewModel: PokemonListViewModel = koinViewModel<PokemonListViewModel>()
        val pokemonListUiState =
            pokemonListViewModel.pokemonListUiState.collectAsStateWithLifecycle()
        val searchUiState: SearchUiState = SearchUiState.Idle
        // by pokemonListViewModel.searchUiState.collectAsStateWithLifecycle()

        sharedTransitionScope.PokemonListScreen(
            animatedVisibilityScope = this,
            drawerState = drawerState,
            currentRoute = currentRoute,
            navigationActions = navigationActions,
            searchUiState = searchUiState,
            pokemonListUiState = pokemonListUiState.value,
            onReload = { /*paginatedPokemonList.refresh()*/ },
            onSearch = { text -> pokemonListViewModel.onPokemonSearch(text) },
            onDismissSearch = { pokemonListViewModel.removeCurrentSearch() },
        )
    }
    composable<Routes.Team> {
        val teamViewModel: TeamViewModel = koinViewModel<TeamViewModel>()
        val uiState: TeamUiState by teamViewModel.uiState.collectAsStateWithLifecycle()

        TeamScreen(
            drawerState = drawerState,
            currentRoute = currentRoute,
            navigationActions = navigationActions,
            uiState = uiState,
            onRetry = { teamViewModel.getTeamList() },
            onSave = { pokemon -> teamViewModel.createPokemonMemberAndReload(pokemon) }
        )
    }
//    composable<Pokemon> { navBackStackEntry ->
//        val pokemonDetailsViewModel: PokemonDetailsViewModel = koinViewModel<PokemonDetailsViewModel>()
//        val teamViewModel: TeamViewModel = koinViewModel<TeamViewModel>()
//        val settingsViewModel: SettingsViewModel = koinViewModel<SettingsViewModel>()
//
//        val pokemonID = navBackStackEntry.toRoute<Pokemon>().id
//        val pokemonDetailsUiState by pokemonDetailsViewModel.detailsUiState.collectAsStateWithLifecycle()
//        val userAppTheme by settingsViewModel.userData.collectAsStateWithLifecycle()
//
//        PokemonDetailsScreen(
//            animatedVisibilityScope = this,
//            pokemon = navBackStackEntry.toRoute<Pokemon>(),
//            pokemonDetailsUiState = pokemonDetailsUiState,
//            userAppTheme = userAppTheme.theme,
//            onFetchDetails = { pokemonDetailsViewModel.getPokemonDetails(pokemonID) },
//            onAddTeamMember = { pokemon, added -> teamViewModel.addPokemonToTeam(pokemon, added) },
//            onBackPressed = { navigationActions.navigateBack() },
//        )
//    }
}