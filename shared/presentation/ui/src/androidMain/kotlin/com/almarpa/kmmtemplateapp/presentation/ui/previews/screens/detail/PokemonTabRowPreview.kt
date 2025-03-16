package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.detail

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.core.ui.theme.AppTheme
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonDetailsMock
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonTabRow

@Composable
@Preview(name = "Pokemon Tab Row", showBackground = true)
@Preview(
    name = "Dark Pokemon Tab Row",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
fun PokemonTabRowPreview() {
    AppTheme {
        PokemonTabRow(pokemonDetails = getPokemonDetailsMock())
    }
}