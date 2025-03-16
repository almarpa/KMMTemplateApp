package com.almarpa.kmmtemplateapp.core.ui.utils

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun BackHandler(onBackPressed: () -> Unit) {
    BackHandler {
        onBackPressed()
    }
}