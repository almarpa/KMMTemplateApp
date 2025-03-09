package com.almarpa.kmmtemplateapp.core.ui.utils

expect fun isTablet(): Boolean

expect fun isLandscapeOrientation(): Boolean

expect fun setAppLanguage(locale: String)

expect fun getDominantColorFromImage(image: Any, onFinish: (Int) -> Unit)