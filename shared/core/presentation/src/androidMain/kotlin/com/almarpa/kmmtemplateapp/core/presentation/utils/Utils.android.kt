package com.almarpa.kmmtemplateapp.core.presentation.utils

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.intl.Locale
import androidx.core.os.LocaleListCompat

@Composable
actual fun isTablet(): Boolean =
    with(LocalConfiguration.current) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            screenWidthDp > 840
        } else {
            screenWidthDp > 600
        }
    }

@Composable
actual fun isLandscapeOrientation(): Boolean =
    LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

actual fun setAppLanguage(locale: String) {
    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(locale))
}

actual fun getDeviceLocale(): String = Locale.current.language