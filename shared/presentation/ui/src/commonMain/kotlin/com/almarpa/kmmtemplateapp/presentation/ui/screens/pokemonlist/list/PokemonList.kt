package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.almarpa.kmmtemplateapp.core.common.extensions.modifierWithLazyGridAnimationPreview
import com.almarpa.kmmtemplateapp.core.presentation.composables.spacer.CustomSpacer
import com.almarpa.kmmtemplateapp.core.presentation.previews.AppThemePreview
import com.almarpa.kmmtemplateapp.core.presentation.utils.isLandscapeOrientation
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonListMock
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.common.PokemonItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonList(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemonList: List<Pokemon>,
    onPokemonItemClick: (Pokemon) -> Unit = { },
) {
    val columns = if (isLandscapeOrientation()) 4 else 2

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            count = pokemonList.size,
            key = { pokemonList[it].id }
        ) { index ->
            PokemonItem(
                modifier = modifierWithLazyGridAnimationPreview(index, columns),
                animatedVisibilityScope = animatedVisibilityScope,
                pokemon = pokemonList[index],
                onPokemonItemClick = { onPokemonItemClick(it) },
            )
            CustomSpacer(height = 16, width = 16)
        }
    }
}

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
fun PokemonListPreview() {
    AppThemePreview {
        PokemonList(
            animatedVisibilityScope = it,
            pokemonList = getPokemonListMock(),
            onPokemonItemClick = { },
        )
    }
}
