package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.search

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.almarpa.kmmtemplateapp.core.ui.composables.error.ErrorPlaceholderView
import com.almarpa.kmmtemplateapp.core.ui.composables.loader.FullScreenLoader
import com.almarpa.kmmtemplateapp.core.ui.composables.notfound.NotFoundView
import com.almarpa.kmmtemplateapp.core.ui.previews.AppThemePreview
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonListMock
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SearchUiState
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.data_not_found
import kmmtemplateapp.shared.presentation.ui.generated.resources.error_getting_pokemon_list
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonSearchBarContent(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    uiState: SearchUiState,
    onSelected: (Pokemon) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            is SearchUiState.Loading -> FullScreenLoader()
            is SearchUiState.NotFound -> NotFoundView(stringResource(Res.string.data_not_found))
            is SearchUiState.Error -> ErrorPlaceholderView(
                errorDescription = stringResource(Res.string.error_getting_pokemon_list),
            )

            is SearchUiState.Success -> {
                PokemonSearchList(
                    animatedVisibilityScope = animatedVisibilityScope,
                    pokemonList = uiState.pokemonList,
                    onPokemonItemClick = { onSelected(it) },
                )
            }

            is SearchUiState.Idle -> Unit
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun PokemonSearchBarContentPreview() {
    AppThemePreview {
        PokemonSearchBarContent(
            animatedVisibilityScope = it,
            uiState = SearchUiState.Success(getPokemonListMock()),
            onSelected = {}
        )
    }
}