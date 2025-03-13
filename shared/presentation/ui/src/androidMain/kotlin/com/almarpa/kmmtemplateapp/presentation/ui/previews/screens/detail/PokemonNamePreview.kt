package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.almarpa.kmmtemplateapp.core.ui.theme.KMMTemplateAppTheme
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonMock
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonName

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PokemonNamePreview() {
    KMMTemplateAppTheme {
        PokemonName(pokemon = getPokemonMock(), modifier = Modifier.padding(top = 150.dp))
    }
}