package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.detail

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.core.ui.theme.KMMTemplateAppTheme
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonDetailsMock
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonMock
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.mockNotFoundAppError
import com.almarpa.kmmtemplateapp.presentation.ui.previews.theme.KMMTemplateAppPreviewTheme
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.AddMemberButton
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonCard
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonDetailsScreen
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonImageAnimation
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonDetailsUiState


@PreviewLightDark
@Composable
fun AddMemberButtonPreview() {
    KMMTemplateAppTheme {
        AddMemberButton()
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview("Pokemon Image Animation")
fun PokemonImageAnimationPreview() {
    KMMTemplateAppPreviewTheme {
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
    KMMTemplateAppPreviewTheme {
        PokemonCard(
            pokemonDetailsUiState = PokemonDetailsUiState.Success(getPokemonDetailsMock()),
            pokemon = getPokemonMock(),
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@PreviewScreenSizes
fun PokemonDetailsScreenPreview() {
    KMMTemplateAppPreviewTheme {
        PokemonDetailsScreen(
            animatedVisibilityScope = it,
            pokemon = getPokemonMock(),
            pokemonDetailsUiState = PokemonDetailsUiState.Success(getPokemonDetailsMock()),
            onFetchDetails = {},
            onAddTeamMember = { _, _ -> },
            onBackPressed = {},
            userAppTheme = AppThemeEnum.DARK,
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@PreviewScreenSizes
fun PokemonDetailsErrorScreenPreview() {
    KMMTemplateAppPreviewTheme {
        PokemonDetailsScreen(
            animatedVisibilityScope = it,
            pokemon = getPokemonMock(),
            pokemonDetailsUiState = PokemonDetailsUiState.Error(mockNotFoundAppError()),
            onFetchDetails = {},
            onAddTeamMember = { _, _ -> },
            onBackPressed = {},
            userAppTheme = AppThemeEnum.DARK,
        )
    }
}
