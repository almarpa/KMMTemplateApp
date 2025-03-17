package com.almarpa.kmmtemplateapp.core.ui.utils

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.intl.Locale

@Composable
actual fun BackHandler(onBackPressed: () -> Unit) {
    BackHandler {
        onBackPressed()
    }
}

actual fun getDeviceLocale(): String = Locale.current.language