package com.almarpa.kmmtemplateapp.core.presentation.theme

import android.app.Activity
import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
actual fun SystemAppearance(darkMode: Boolean) {
    val view = LocalView.current
    val systemBarColor = Color.TRANSPARENT
    LaunchedEffect(darkMode) {
        val window = (view.context as Activity).window
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = systemBarColor
        window.navigationBarColor = systemBarColor
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = darkMode
            isAppearanceLightNavigationBars = darkMode
        }
    }
}
