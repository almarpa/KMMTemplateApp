package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.common

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.almarpa.kmmtemplateapp.core.common.extensions.modifierWithSharedElementTransition
import com.almarpa.kmmtemplateapp.core.common.extensions.shimmerLoadingAnimation
import com.almarpa.kmmtemplateapp.core.presentation.animations.WithAnimatedVisibilityScope
import com.almarpa.kmmtemplateapp.core.presentation.animations.WithSharedTransitionScope
import com.almarpa.kmmtemplateapp.core.presentation.previews.AppThemePreview
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.kmpalette.loader.rememberNetworkLoader
import com.kmpalette.rememberDominantColorState
import io.ktor.http.Url
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onPokemonItemClick: (Pokemon) -> Unit = { },
) {
    val themeDefaultColor = MaterialTheme.colorScheme.onSurface
    val initialColor = remember(pokemon.id, themeDefaultColor) {
        if (pokemon.color != 0) Color(pokemon.color) else themeDefaultColor
    }
    val networkLoader = rememberNetworkLoader()
    val dominantColorState = rememberDominantColorState(
        loader = networkLoader,
        defaultColor = initialColor,
    )

    val isColorLoaded = remember(pokemon.color, dominantColorState.result) {
        pokemon.color != 0 || dominantColorState.result != null
    }

    LaunchedEffect(pokemon.url) {
        if (pokemon.color == 0) {
            dominantColorState.updateFrom(Url(pokemon.url))
        }
    }

    LaunchedEffect(dominantColorState.result) {
        if (dominantColorState.result != null) {
            pokemon.color = dominantColorState.color.toArgb()
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onPokemonItemClick(pokemon) },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = dominantColorState.color
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shimmerLoadingAnimation(
                    isLoadingCompleted = isColorLoaded
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            WithSharedTransitionScope {
                WithAnimatedVisibilityScope {
                    SubcomposeAsyncImage(
                        model = pokemon.url,
                        contentDescription = "Pokemon Image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth(.8f)
                            .aspectRatio(1f)
                            .padding(10.dp)
                            .clip(shape = RoundedCornerShape(40.dp))
                            .then(
                                modifierWithSharedElementTransition(
                                    state = rememberSharedContentState(key = "item-image${pokemon.id}"),
                                    animatedVisibilityScope = this,
                                )
                            )
                    )
                }
            }

            Text(
                text = pokemon.name.uppercase().takeIf { isColorLoaded }.orEmpty(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                minLines = 2,
                maxLines = 2,
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = 12.dp),
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview
fun PokemonItemPreview() {
    AppThemePreview {
        PokemonItem(
            pokemon = Pokemon(
                id = 1,
                url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png",
                name = "Pokemon name Pokemon name Pokemon name Pokemon name",
            )
        )
    }
}