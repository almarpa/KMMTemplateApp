package com.almarpa.kmmtemplateapp.core.presentation.animations

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> {
    null
}

@Composable
fun AnimatedVisibilityScope.ProvideAnimatedVisibilityScope(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAnimatedVisibilityScope provides this,
        content = content
    )
}

@Composable
fun WithAnimatedVisibilityScope(block: @Composable AnimatedVisibilityScope.() -> Unit) {
    val scope = LocalAnimatedVisibilityScope.current
    if (scope != null) {
        with(scope) {
            block()
        }
    }
}
