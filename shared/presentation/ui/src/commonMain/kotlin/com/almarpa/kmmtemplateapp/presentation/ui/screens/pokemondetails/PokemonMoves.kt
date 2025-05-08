package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.almarpa.kmmtemplateapp.domain.models.Move
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonMoveListMock
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PokemonMoves(moves: List<Move>) {
    val firstFourMoves = moves.take(4)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            count = firstFourMoves.size,
            key = { firstFourMoves[it].move.name }
        ) {
            PokemonMove(move = firstFourMoves[it])
        }
    }
}

@Composable
fun PokemonMove(move: Move) {
    Card(
        modifier = Modifier.height(60.dp),
        shape = CutCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(vertical = 12.dp, horizontal = 24.dp)
                    .align(Alignment.Center),
                text = move.move.name.uppercase(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview()
@Composable
fun PokemonMovesPreview() {
    PokemonMoves(moves = getPokemonMoveListMock())
}