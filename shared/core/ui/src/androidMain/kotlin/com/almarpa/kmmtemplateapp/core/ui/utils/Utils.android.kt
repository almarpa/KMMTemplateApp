package com.almarpa.kmmtemplateapp.core.ui.utils

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.palette.graphics.Palette
import coil3.BitmapImage

actual fun isTablet(): Boolean = false
// TODO: expect fun
//    val configuration = LocalConfiguration.current
//    return if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//        configuration.screenWidthDp > 840
//    } else {
//        configuration.screenWidthDp > 600
//    }

actual fun isLandscapeOrientation(): Boolean = false
//getApplicationContext().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

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