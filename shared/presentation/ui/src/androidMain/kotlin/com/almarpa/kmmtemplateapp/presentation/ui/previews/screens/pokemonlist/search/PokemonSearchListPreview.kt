package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.pokemonlist.search

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.presentation.ui.previews.theme.KMMTemplateAppPreviewTheme
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonListMock
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.search.PokemonSearchList

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview("Pokemon List", showBackground = true)
@Preview("Pokemon List", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PokemonListPreview() {
    KMMTemplateAppPreviewTheme {
        PokemonSearchList(
            animatedVisibilityScope = it,
            pokemonList = getPokemonListMock(),
            onPokemonItemClick = { },
        )
    }
}
