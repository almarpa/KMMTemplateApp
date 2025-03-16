package com.almarpa.kmmtemplateapp.core.ui.utils

import androidx.compose.runtime.Composable
import platform.UIKit.UIApplication
import platform.UIKit.UIGestureRecognizer
import platform.UIKit.UIGestureRecognizerDelegateProtocol
import platform.UIKit.UIViewController
import platform.UIKit.navigationController
import platform.darwin.NSObject

@Composable
actual fun BackHandler(onBackPressed: () -> Unit) {
    setBackGestureListener { onBackPressed() }
}

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