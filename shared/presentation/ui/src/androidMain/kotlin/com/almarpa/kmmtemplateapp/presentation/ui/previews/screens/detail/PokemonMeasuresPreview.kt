package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonMeasureItem
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonMeasures

@PreviewLightDark
@Composable
fun PokemonMeasuresPreview() {
    PokemonMeasures()
}

@Composable
@PreviewLightDark
fun PokemonMeasurePreview() {
    PokemonMeasureItem()
}