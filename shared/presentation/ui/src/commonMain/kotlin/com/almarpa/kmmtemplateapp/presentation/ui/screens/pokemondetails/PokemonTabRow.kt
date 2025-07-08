package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.almarpa.kmmtemplateapp.core.presentation.composables.tabrow.SwipeableTabRow
import com.almarpa.kmmtemplateapp.core.presentation.theme.AppTheme
import com.almarpa.kmmtemplateapp.domain.models.PokemonDetails
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonDetailsMock
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.moves
import kmmtemplateapp.shared.presentation.ui.generated.resources.stats
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PokemonTabRow(modifier: Modifier = Modifier, pokemonDetails: PokemonDetails) {
    Box(modifier = modifier) {
        SwipeableTabRow(
            modifier = Modifier.fillMaxSize(),
            tabs = listOf(
                stringResource(Res.string.stats),
                stringResource(Res.string.moves),
            ),
            contentScreens = listOf(
                { PokemonStats(stats = pokemonDetails.stats) },
                { PokemonMoves(moves = pokemonDetails.moves) },
            ),
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            indicatorColor = Color.DarkGray
        )
    }
}


@Composable
@Preview
fun PokemonTabRowPreview() {
    AppTheme {
        PokemonTabRow(pokemonDetails = getPokemonDetailsMock())
    }
}