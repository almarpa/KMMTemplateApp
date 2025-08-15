package com.almarpa.kmmtemplateapp.core.presentation.theme

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
actual fun SystemAppearance(darkMode: Boolean) {
    val view = LocalView.current
    val context = LocalContext.current
    val window = (context as Activity).window

    LaunchedEffect(darkMode) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, view).apply {
            isAppearanceLightStatusBars = darkMode
            isAppearanceLightNavigationBars = darkMode
        }
    }
}
