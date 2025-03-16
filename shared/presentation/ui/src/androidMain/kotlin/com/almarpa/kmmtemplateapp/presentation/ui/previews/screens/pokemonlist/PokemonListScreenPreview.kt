package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.pokemonlist

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonListMock
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.NavigationActions
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.previews.theme.AppThemePreview
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.PokemonListScreen
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonListUiState
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SearchUiState

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview("Pokemon List Screen")
@Preview("Pokemon List Screen", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PokemonListScreenPreview() {
    AppThemePreview {
        PokemonListScreen(
            animatedVisibilityScope = it,
            drawerState = DrawerState(DrawerValue.Closed),
            currentRoute = Routes.PokemonList,
            navigationActions = NavigationActions(rememberNavController()),
            searchUiState = SearchUiState.Success(getPokemonListMock()),
            pokemonListUiState = PokemonListUiState.Success(getPokemonListMock()),
            onReload = {},
            onDismissSearch = {},
            onSearch = {},
        )
    }
}

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview("Pokemon List Screen")
@Preview("Pokemon List Screen", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PokemonListScreenWithSearchActivePreview() {
    AppThemePreview {
        PokemonListScreen(
            animatedVisibilityScope = it,
            drawerState = DrawerState(DrawerValue.Closed),
            currentRoute = Routes.PokemonList,
            navigationActions = NavigationActions(rememberNavController()),
            searchUiState = SearchUiState.Error,
            pokemonListUiState = PokemonListUiState.Success(getPokemonListMock()),
            onReload = {},
            onDismissSearch = {},
            onSearch = {},
        )
    }
}