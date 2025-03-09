package com.almarpa.kmmtemplateapp.presentation.ui.previews.common

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.core.ui.composables.notfound.NotFoundView
import com.almarpa.kmmtemplateapp.core.ui.theme.KMMTemplateAppTheme

@Preview("Not Found View")
@Preview("Not Found View Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NotFoundViewPreview() {
    KMMTemplateAppTheme {
        NotFoundView("Not found")
    }
}