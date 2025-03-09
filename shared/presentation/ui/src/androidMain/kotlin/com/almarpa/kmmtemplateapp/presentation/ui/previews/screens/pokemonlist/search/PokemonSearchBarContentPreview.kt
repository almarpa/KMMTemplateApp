package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.pokemonlist.search

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.presentation.ui.previews.theme.KMMTemplateAppPreviewTheme
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonListMock
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.search.PokemonSearchBarContent
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SearchUiState

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview("Pokemon Search Bar Content")
@Preview(
    "Dark Pokemon Search Bar Content",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PokemonSearchBarContentPreview() {
    KMMTemplateAppPreviewTheme {
        PokemonSearchBarContent(
            animatedVisibilityScope = it,
            uiState = SearchUiState.Success(getPokemonListMock()),
            onSelected = {}
        )
    }
}