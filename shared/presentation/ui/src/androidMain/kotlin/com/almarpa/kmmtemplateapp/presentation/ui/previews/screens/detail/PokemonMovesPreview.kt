package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonMoveListMock
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonMoves

@Preview("Pokemon Moves")
@Composable
fun PokemonMovesPreview() {
    PokemonMoves(moves = getPokemonMoveListMock())
}