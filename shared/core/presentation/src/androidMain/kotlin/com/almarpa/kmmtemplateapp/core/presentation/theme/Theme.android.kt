package com.almarpa.kmmtemplateapp.core.presentation.theme

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
actual fun SystemAppearance(darkMode: Boolean) {
    val view = LocalView.current

    LaunchedEffect(darkMode) {
        val window = (view.context as Activity).window
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = darkMode
            isAppearanceLightNavigationBars = darkMode
        }
    }
}
