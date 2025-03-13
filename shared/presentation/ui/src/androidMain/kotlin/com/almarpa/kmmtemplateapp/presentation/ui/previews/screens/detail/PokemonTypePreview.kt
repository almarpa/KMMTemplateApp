package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.domain.models.TypeX
import com.almarpa.kmmtemplateapp.domain.models.TypeXX
import com.almarpa.kmmtemplateapp.domain.models.enums.PokemonTypeEnum
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonType

@Composable
@Preview("Pokemon Type")
fun PokemonTypePreview() {
    PokemonType(
        types = listOf(
            TypeX(1, TypeXX(PokemonTypeEnum.ICE, "")),
            TypeX(2, TypeXX(PokemonTypeEnum.PSYCHIC, "")),
        )
    )
}