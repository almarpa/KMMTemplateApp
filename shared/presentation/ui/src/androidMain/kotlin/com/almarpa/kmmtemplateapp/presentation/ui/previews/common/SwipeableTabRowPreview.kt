package com.almarpa.kmmtemplateapp.presentation.ui.previews.common

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.core.ui.composables.tabrow.SwipeableTabRow
import com.almarpa.kmmtemplateapp.core.ui.theme.KMMTemplateAppTheme
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonStatListMock
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonStats

@Composable
@Preview(name = "Swipeable Tab Row", showBackground = true)
@Preview(
    name = "Dark Swipeable Tab Row",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
fun SwipeableTabRowPreview() {
    KMMTemplateAppTheme {
        SwipeableTabRow(
            modifier = Modifier,
            tabs = listOf("Stats", "Moves"),
            contentScreens = listOf(
                { PokemonStats(stats = getPokemonStatListMock()) },
                { PokemonStats(stats = getPokemonStatListMock()) },
            ),
            containerColor = Color.White,
            contentColor = Color.Black,
            indicatorColor = Color.Black
        )
    }
}