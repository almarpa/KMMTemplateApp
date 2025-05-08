package com.almarpa.kmmtemplateapp.presentation.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.almarpa.kmmtemplateapp.core.ui.theme.AppTheme
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.pokeball_filled
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SplashScreen(navigateToPokemonList: () -> Unit = {}) {
    val rotationState = remember { Animatable(0f) }
    val splashAnimationFinished = remember { mutableStateOf(false) }
    val animDuration = 2000

    LaunchedEffect(Unit) {
        rotationState.animateTo(
            targetValue = 740f,
            animationSpec = tween(durationMillis = animDuration),
        ) {
            launch {
                delay(animDuration.toLong())
                splashAnimationFinished.value = true
            }
        }
    }

    if (!splashAnimationFinished.value) {
        SplashContent(rotationState)
    } else {
        navigateToPokemonList()
    }
}

@Composable
fun SplashContent(rotationState: Animatable<Float, AnimationVector1D>) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .graphicsLayer(rotationZ = rotationState.value),
    ) {
        Image(
            modifier = Modifier
                .border(2.dp, Color.Black, CircleShape)
                .width(50.dp)
                .scale(1.3f),
            painter = painterResource(Res.drawable.pokeball_filled),
            contentDescription = "PokeballImage",
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    AppTheme {
        SplashScreen()
    }
}
