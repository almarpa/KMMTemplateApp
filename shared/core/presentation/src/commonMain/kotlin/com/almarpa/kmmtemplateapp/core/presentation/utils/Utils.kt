package com.almarpa.kmmtemplateapp.core.presentation.utils

import androidx.compose.runtime.Composable

@Composable
expect fun isTablet(): Boolean

@Composable
expect fun isLandscapeOrientation(): Boolean

expect fun setAppLanguage(locale: String)

expect fun getDeviceLocale(): String

