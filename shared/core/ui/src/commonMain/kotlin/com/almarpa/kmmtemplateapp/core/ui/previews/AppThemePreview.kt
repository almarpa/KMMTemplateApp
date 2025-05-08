package com.almarpa.kmmtemplateapp.core.ui.previews

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import com.almarpa.kmmtemplateapp.core.ui.theme.AppTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppThemePreview(
    content: @Composable SharedTransitionScope.(AnimatedVisibilityScope) -> Unit,
) {
    AppTheme {
        SharedTransitionScope {
            AnimatedVisibility(visible = true, label = "") {
                content(this)
            }
        }
    }
}