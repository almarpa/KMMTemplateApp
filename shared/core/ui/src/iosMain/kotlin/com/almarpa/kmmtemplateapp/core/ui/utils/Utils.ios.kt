package com.almarpa.kmmtemplateapp.core.ui.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectGetHeight
import platform.CoreGraphics.CGRectGetWidth
import platform.UIKit.UIScreen

actual fun isTablet() = false

@OptIn(ExperimentalForeignApi::class)
actual fun isLandscapeOrientation(): Boolean {
    val screenSize = UIScreen.mainScreen.bounds
    return CGRectGetWidth(screenSize) > CGRectGetHeight(screenSize)
}

actual fun setAppLanguage(locale: String) {}

actual fun getDominantColorFromImage(image: Any, onFinish: (Int) -> Unit) {
//    if (image !is UIImage) throw IllegalArgumentException("Expected a UIImage")
//
//    val color: UIColor = SwiftUtils.getDominantColor(image)
//    val components = CGColorGetComponents(color.cgColor) ?: return 0xFFFFFF
//
//    val red = (components[0] * 255).toInt()
//    val green = (components[1] * 255).toInt()
//    val blue = (components[2] * 255).toInt()
//
//    onFinish((red shl 16) or (green shl 8) or blue)
    onFinish(Color.Black.toArgb())
}