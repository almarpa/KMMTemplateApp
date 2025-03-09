package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.splash

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.core.ui.theme.KMMTemplateAppTheme
import com.almarpa.kmmtemplateapp.presentation.ui.screens.splash.SplashScreen

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenPreview() {
    KMMTemplateAppTheme {
        SplashScreen()
    }
}
