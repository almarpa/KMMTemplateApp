package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.pokemonlist.common

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.previews.theme.KMMTemplateAppPreviewTheme
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.common.PokemonItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview("Pokemon Item View")
fun PokemonItemPreview() {
    KMMTemplateAppPreviewTheme {
        PokemonItem(
            animatedVisibilityScope = it,
            pokemon = Pokemon(
                id = 1,
                url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png",
                name = "Pokemon name Pokemon name Pokemon name Pokemon name",
            )
        )
    }
}