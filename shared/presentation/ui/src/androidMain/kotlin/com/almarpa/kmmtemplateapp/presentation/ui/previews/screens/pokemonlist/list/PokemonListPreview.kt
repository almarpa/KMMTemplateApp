package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.pokemonlist.list

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.presentation.ui.previews.theme.KMMTemplateAppPreviewTheme
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonListMock
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.list.PokemonList

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview("Pokemon List")
@Preview("Dark Pokemon List", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Dark Pokemon List", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PokemonListPreview() {
    KMMTemplateAppPreviewTheme {
        PokemonList(
            animatedVisibilityScope = it,
            pokemonList = getPokemonListMock(),
            onPokemonItemClick = { },
        )
    }
}