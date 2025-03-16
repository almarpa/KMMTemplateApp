package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.pokemonlist.search

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonListMock
import com.almarpa.kmmtemplateapp.presentation.ui.previews.theme.AppThemePreview
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.search.PokemonSearchBar
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SearchUiState

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview("Search Bar")
@Preview(
    "Dark Search Bar",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ActivatedSearchTopBarPreview() {
    AppThemePreview {
        PokemonSearchBar(
            animatedVisibilityScope = it,
            uiState = SearchUiState.Success(getPokemonListMock()),
            onCancel = {},
            onSearch = {},
            onSelected = {}
        )
    }
}