package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.detail

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.almarpa.kmmtemplateapp.core.ui.theme.AppTheme
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonDetailsMock
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonMock
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.mockNotFoundAppError
import com.almarpa.kmmtemplateapp.presentation.ui.previews.theme.AppThemePreview
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.AddMemberButton
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonCard
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonDetailsScreen
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonImageAnimation
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonDetailsUiState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@PreviewScreenSizes
fun PokemonDetailsScreenPreview() {
    AppThemePreview {
        PokemonDetailsScreen(
            animatedVisibilityScope = it,
            pokemon = getPokemonMock(),
            pokemonDetailsUiState = PokemonDetailsUiState.Success(getPokemonDetailsMock()),
            onFetchDetails = {},
            onAddTeamMember = { _, _ -> },
            onBackPressed = {},
        )
    }
}

@Composable
@PreviewLightDark
fun AddMemberButtonPreview() {
    AppTheme {
        AddMemberButton()
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@PreviewLightDark
fun PokemonImageAnimationPreview() {
    AppThemePreview {
        PokemonImageAnimation(
            animatedVisibilityScope = it,
            pokemon = getPokemonMock(),
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@PreviewLightDark
fun PokemonCardPreview() {
    AppThemePreview {
        PokemonCard(
            pokemonDetailsUiState = PokemonDetailsUiState.Success(getPokemonDetailsMock()),
            pokemon = getPokemonMock(),
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@PreviewLightDark
fun PokemonDetailsErrorScreenPreview() {
    AppThemePreview {
        PokemonDetailsScreen(
            animatedVisibilityScope = it,
            pokemon = getPokemonMock(),
            pokemonDetailsUiState = PokemonDetailsUiState.Error(mockNotFoundAppError()),
            onFetchDetails = {},
            onAddTeamMember = { _, _ -> },
            onBackPressed = {},
        )
    }
}
