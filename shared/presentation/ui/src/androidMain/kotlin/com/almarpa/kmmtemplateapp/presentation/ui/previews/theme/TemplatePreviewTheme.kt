package com.almarpa.kmmtemplateapp.presentation.ui.previews.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import com.almarpa.kmmtemplateapp.core.ui.theme.KMMTemplateAppTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun KMMTemplateAppPreviewTheme(
    content: @Composable SharedTransitionScope.(AnimatedVisibilityScope) -> Unit,
) {
    KMMTemplateAppTheme {
        SharedTransitionScope {
            AnimatedVisibility(visible = true, label = "") {
                content(this)
            }
        }
    }
}