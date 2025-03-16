package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.pokemonlist.search

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonListMock
import com.almarpa.kmmtemplateapp.presentation.ui.previews.theme.AppThemePreview
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.search.PokemonSearchTopAppBar
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SearchUiState

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Preview("Inactive Search Top App Bar")
@Preview("Inactive Dark Search Top App Bar", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InactiveSearchTopAppBarPreview() {
    AppThemePreview {
        PokemonSearchTopAppBar(
            animatedVisibilityScope = it,
            uiState = SearchUiState.Success(getPokemonListMock()),
            drawerState = DrawerState(DrawerValue.Closed),
        ) {}
    }
}

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Preview("Active Search Top App Bar")
@Preview("Active Dark Search Top App Bar", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ActiveSearchTopAppBarPreview() {
    AppThemePreview {
        PokemonSearchTopAppBar(
            animatedVisibilityScope = it,
            uiState = SearchUiState.Success(getPokemonListMock()),
            drawerState = DrawerState(DrawerValue.Closed),
            isSearchActive = true
        ) {}
    }
}


