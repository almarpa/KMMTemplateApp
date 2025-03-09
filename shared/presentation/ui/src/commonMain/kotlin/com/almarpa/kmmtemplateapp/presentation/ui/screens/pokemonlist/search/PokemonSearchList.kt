package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.search

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.almarpa.kmmtemplateapp.core.common.extensions.applyIfCurrentLocalInspectionMode
import com.almarpa.kmmtemplateapp.core.ui.composables.animations.getLazyGridAnimation
import com.almarpa.kmmtemplateapp.core.ui.composables.spacer.CustomSpacer
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.common.PokemonItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonSearchList(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemonList: List<Pokemon>,
    onPokemonItemClick: (Pokemon) -> Unit = { },
) {
//    val currentOrientation = LocalConfiguration.current.orientation
    val columns = 2//if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 2

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.Transparent),
    ) {
        items(
            count = pokemonList.size,
            key = { pokemonList[it].id }
        ) { index ->
            val gridAnim = getLazyGridAnimation(index, columns)
            PokemonItem(
                modifier = Modifier.applyIfCurrentLocalInspectionMode {
                    graphicsLayer(
                        alpha = gridAnim.first,
                        scaleX = gridAnim.second,
                        scaleY = gridAnim.second
                    )
                },
                animatedVisibilityScope = animatedVisibilityScope,
                pokemon = pokemonList[index],
                onPokemonItemClick = { onPokemonItemClick(it) },
            )
            CustomSpacer(height = 16, width = 16)
        }
    }
}