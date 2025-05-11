package com.almarpa.kmmtemplateapp.core.ui.utils

import androidx.compose.runtime.Composable
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectGetHeight
import platform.CoreGraphics.CGRectGetWidth
import platform.Foundation.NSLocale
import platform.Foundation.NSUserDefaults
import platform.Foundation.currentLocale
import platform.Foundation.languageCode
import platform.UIKit.UIDevice
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceIdiomPad

@Composable
actual fun isTablet() = UIDevice.currentDevice.userInterfaceIdiom == UIUserInterfaceIdiomPad

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun isLandscapeOrientation(): Boolean =
    with(UIScreen.mainScreen.bounds) { CGRectGetWidth(this) > CGRectGetHeight(this) }

actual fun setAppLanguage(locale: String) {
    NSUserDefaults.standardUserDefaults.setObject(
        arrayListOf(locale), "AppleLanguages"
    )
}

actual fun getDeviceLocale(): String = NSLocale.currentLocale.languageCode