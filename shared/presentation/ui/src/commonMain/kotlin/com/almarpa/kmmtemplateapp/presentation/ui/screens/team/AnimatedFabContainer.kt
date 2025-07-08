package com.almarpa.kmmtemplateapp.presentation.ui.screens.team

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.SubcomposeAsyncImage
import com.almarpa.kmmtemplateapp.core.presentation.composables.spacer.CustomSpacer
import com.almarpa.kmmtemplateapp.core.presentation.theme.AppTheme
import com.almarpa.kmmtemplateapp.core.presentation.utils.isTablet
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.kmpalette.loader.rememberNetworkLoader
import com.kmpalette.rememberDominantColorState
import io.ktor.http.Url
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.add_photo
import kmmtemplateapp.shared.presentation.ui.generated.resources.common_save
import kmmtemplateapp.shared.presentation.ui.generated.resources.insert_name
import kmmtemplateapp.shared.presentation.ui.generated.resources.menu_drawer_btn
import kmmtemplateapp.shared.presentation.ui.generated.resources.pokeball_filled
import kmmtemplateapp.shared.presentation.ui.generated.resources.pokemon_name
import kmmtemplateapp.shared.presentation.ui.generated.resources.pokemon_photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AnimatedFabContainer(
    modifier: Modifier = Modifier,
    fabContainerState: Boolean,
    onFabContainerStateChanged: (Boolean) -> Unit,
    onSave: (Pokemon) -> Unit,
) {
    with(updateTransition(targetState = fabContainerState, label = "fabContainerTransition")) {
        val backgroundColor = getBackgroundColor()
        val cornerRadius = getCornerRadius()
        AnimatedContent(
            modifier = modifier
                .shadow(0.dp, RoundedCornerShape(cornerRadius))
                .drawBehind { drawRect(backgroundColor) },
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(1000, easing = EaseIn)
                ).togetherWith(
                    fadeOut(
                        animationSpec = tween(200, easing = EaseOut)
                    )
                )
            },
        ) { state ->
            if (state) {
                AddPokemonFullscreen(
                    onCancel = { onFabContainerStateChanged(false) },
                    onSave = { pokemon -> onSave(pokemon) }
                )
            } else {
                AddPokemonFab {
                    onFabContainerStateChanged(true)
                }
            }
        }
    }
}

@Composable
private fun Transition<Boolean>.getBackgroundColor(): Color {
    val backgroundColor by animateColor(label = "fabContainerColorAnim") { state ->
        if (state) {
            MaterialTheme.colorScheme.surface
        } else {
            Color.Transparent
        }
    }
    return backgroundColor
}

@Composable
private fun Transition<Boolean>.getCornerRadius(): Dp {
    val cornerRadius by animateDp(label = "fabContainerDpAnim") { state ->
        if (state) {
            0.dp
        } else {
            22.dp
        }
    }
    return cornerRadius
}

@Composable
fun AddPokemonFullscreen(onCancel: () -> Unit, onSave: (Pokemon) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 12.dp)
    ) {
        PokemonForm { pokemon -> onSave(pokemon) }
        CustomBackButton { onCancel() }
    }
}

@Composable
fun PokemonForm(onSave: (Pokemon) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var pokemonImageUrl: String by rememberSaveable { mutableStateOf("") }
        var pokemonImageUrlToLoad: String by rememberSaveable { mutableStateOf("") }
        var pokemonName: String by rememberSaveable { mutableStateOf("") }
        var pokemonColor: Color by remember { mutableStateOf(Color.Transparent) }
        var validImageURL: Boolean by rememberSaveable { mutableStateOf(false) }

        LaunchedEffect(pokemonImageUrl) {
            delay(1000L)
            pokemonImageUrlToLoad = pokemonImageUrl
        }

        PokemonImageCard(
            pokemonImageURL = pokemonImageUrlToLoad,
            onError = {
                pokemonColor = Color.Transparent
                validImageURL = false
            },
            onSuccess = {
                pokemonColor = it
                validImageURL = true
            }
        )
        CustomSpacer(modifier = Modifier.weight(1f))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(.9f),
            value = pokemonImageUrl,
            onValueChange = { pokemonImageUrl = it },
            placeholder = { Text(text = stringResource(Res.string.add_photo)) },
            label = { Text(text = stringResource(Res.string.pokemon_photo)) },
            maxLines = if (isTablet()) 1 else 3,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            isError = !validImageURL && pokemonImageUrl.isNotEmpty(),
        )
        CustomSpacer(height = 20)
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(.9f),
            value = pokemonName,
            onValueChange = { pokemonName = it },
            placeholder = { Text(text = stringResource(Res.string.insert_name)) },
            label = { Text(text = stringResource(Res.string.pokemon_name)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            maxLines = if (isTablet()) 1 else 2,
        )
        CustomSpacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(.6f)
                .padding(vertical = 20.dp),
            enabled = checkFields(pokemonName, pokemonImageUrl, validImageURL),
            onClick = {
                onSave(
                    Pokemon(
                        url = pokemonImageUrl,
                        name = pokemonName,
                        color = pokemonColor.toArgb(),
                    )
                )
            },
        ) {
            Text(
                modifier = Modifier.padding(vertical = 6.dp),
                text = stringResource(Res.string.common_save)
            )
        }
        CustomSpacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun PokemonImageCard(
    pokemonImageURL: String,
    onError: () -> Unit,
    onSuccess: (Color) -> Unit,
) {
    val defaultDominantColor = MaterialTheme.colorScheme.primary
    val networkLoader = rememberNetworkLoader()
    val dominantColorState = rememberDominantColorState(
        loader = networkLoader,
        defaultColor = defaultDominantColor,
        coroutineContext = Dispatchers.IO,
    )

    LaunchedEffect(pokemonImageURL) {
        dominantColorState.updateFrom(Url(pokemonImageURL))
    }

    LaunchedEffect(dominantColorState.color) {
        onSuccess(dominantColorState.color)
    }

    Card(
        modifier = Modifier
            .padding(20.dp),
        shape = AbsoluteCutCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = dominantColorState.color)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth(if (isTablet()) .3f else .8f)
                .aspectRatio(1f),
            model = pokemonImageURL,
            contentDescription = "Member Image",
            contentScale = ContentScale.FillBounds,
            error = {
                Image(
                    modifier = Modifier.width(50.dp),
                    painter = painterResource(Res.drawable.pokeball_filled),
                    contentDescription = "PokeballImage",
                )
            },
            onError = {
                onError()
            },
        )
    }

}

fun checkFields(pokemonName: String, pokemonImageUrl: String?, validImageURL: Boolean) =
    !pokemonImageUrl.isNullOrEmpty() && pokemonName.isNotEmpty() && validImageURL

@Composable
fun CustomBackButton(onCancel: () -> Unit) {
    IconButton(
        modifier = Modifier
            .height(32.dp)
            .aspectRatio(1f),
        onClick = { onCancel() }
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            tint = MaterialTheme.colorScheme.tertiary,
            contentDescription = null
        )
    }
}

@Composable
fun AddPokemonFab(onFabButtonPressed: () -> Unit) {
    Button(
        modifier = Modifier.padding(end = 16.dp, bottom = 16.dp),
        onClick = { onFabButtonPressed() },
    ) {
        Box {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .offset(x = (-16).dp, y = (-12).dp)
                    .zIndex(1f),
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(Res.string.menu_drawer_btn),
                tint = Color.White
            )
            Image(
                modifier = Modifier.width(50.dp),
                painter = painterResource(Res.drawable.pokeball_filled),
                contentDescription = "PokeballImage",
            )
        }
    }
}

@Composable
@Preview
fun AddPokemonFloatingButtonPreview() {
    AppTheme {
        AnimatedFabContainer(
            fabContainerState = false,
            onFabContainerStateChanged = {},
            onSave = {}
        )
    }
}

@Composable
@Preview
fun FabContainerFullscreenPreview() {
    AppTheme {
        AnimatedFabContainer(
            fabContainerState = true,
            onFabContainerStateChanged = {},
            onSave = {}
        )
    }
}