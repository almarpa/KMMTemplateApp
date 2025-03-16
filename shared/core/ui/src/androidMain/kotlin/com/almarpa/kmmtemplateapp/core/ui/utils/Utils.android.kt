package com.almarpa.kmmtemplateapp.core.ui.utils

import android.content.res.Configuration
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat
import androidx.palette.graphics.Palette
import coil3.BitmapImage

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
    LocalContext.current.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

actual fun setAppLanguage(locale: String) {
    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(locale))
}

actual fun getDominantColorFromImage(
    image: Any,
    onFinish: (Int) -> Unit
) {
    (image as? BitmapImage)?.bitmap?.copy(Bitmap.Config.ARGB_8888, true)?.let { bitmap ->
        Palette.from(bitmap).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(colorValue)
            }
        }
    }
}