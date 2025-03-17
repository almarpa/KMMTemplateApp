package com.almarpa.kmmtemplateapp.core.ui.utils

import androidx.compose.runtime.Composable
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectGetHeight
import platform.CoreGraphics.CGRectGetWidth
import platform.Foundation.NSLocale
import platform.Foundation.NSUserDefaults
import platform.Foundation.currentLocale
import platform.Foundation.languageCode
import platform.Foundation.setValue
import platform.UIKit.UIApplication
import platform.UIKit.UIDevice
import platform.UIKit.UIGestureRecognizer
import platform.UIKit.UIGestureRecognizerDelegateProtocol
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceIdiomPad
import platform.UIKit.UIViewController
import platform.UIKit.navigationController
import platform.darwin.NSObject

@Composable
actual fun isTablet() = UIDevice.currentDevice.userInterfaceIdiom == UIUserInterfaceIdiomPad

@Composable
actual fun BackHandler(onBackPressed: () -> Unit) {
    setBackGestureListener { onBackPressed() }
}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun isLandscapeOrientation(): Boolean =
    with(UIScreen.mainScreen.bounds) { CGRectGetWidth(this) > CGRectGetHeight(this) }

actual fun setAppLanguage(locale: String) {
    NSUserDefaults.standardUserDefaults.setValue(locale, forKey = "AppleLanguages")
}

actual fun getDeviceLocale(): String = NSLocale.currentLocale.languageCode

fun setBackGestureListener(onBack: () -> Unit) {
    getViewController()?.navigationController?.interactivePopGestureRecognizer?.delegate =
        object : NSObject(), UIGestureRecognizerDelegateProtocol {
            override fun gestureRecognizerShouldBegin(gestureRecognizer: UIGestureRecognizer): Boolean {
                onBack()
                return false
            }
        }
}

fun getViewController(): UIViewController? {
    return UIApplication.sharedApplication.keyWindow?.rootViewController
}