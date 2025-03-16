package com.almarpa.kmmtemplateapp.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import platform.UIKit.UIApplication
import platform.UIKit.UIStatusBarStyleDarkContent
import platform.UIKit.UIStatusBarStyleLightContent
import platform.UIKit.setStatusBarStyle

@Composable
actual fun SystemAppearance(darkMode: Boolean) {
    LaunchedEffect(darkMode) {
        UIApplication.sharedApplication.setStatusBarStyle(
            if (darkMode) UIStatusBarStyleDarkContent else UIStatusBarStyleLightContent
        )
    }
}
