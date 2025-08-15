@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.almarpa.kmmtemplateapp.core.presentation.previews

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import com.almarpa.kmmtemplateapp.core.presentation.animations.ProvideAnimatedVisibilityScope
import com.almarpa.kmmtemplateapp.core.presentation.animations.ProvideSharedTransitionScope
import com.almarpa.kmmtemplateapp.core.presentation.theme.AppTheme

@Composable
fun AppThemePreview(
    content: @Composable SharedTransitionScope.(AnimatedVisibilityScope) -> Unit,
) {
    AppTheme {
        SharedTransitionScope {
            AnimatedVisibility(visible = true, label = "") {
                ProvideSharedTransitionScope {
                    ProvideAnimatedVisibilityScope {
                        content(this)
                    }
                }
            }
        }
    }
}