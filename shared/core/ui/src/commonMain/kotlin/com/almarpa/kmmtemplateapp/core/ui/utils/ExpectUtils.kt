package com.almarpa.kmmtemplateapp.core.ui.utils

import androidx.compose.runtime.Composable

@Composable
expect fun isTablet(): Boolean

@Composable
expect fun isLandscapeOrientation(): Boolean

expect fun setAppLanguage(locale: String)

expect fun getDominantColorFromImage(image: Any, onFinish: (Int) -> Unit)

@Composable
expect fun BackHandler(onBackPressed: () -> Unit)

expect fun getDeviceLocale(): String
