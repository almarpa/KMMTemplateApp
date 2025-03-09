package com.almarpa.kmmtemplateapp.presentation.ui.previews.common

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.core.ui.composables.topappbar.DefaultTopAppBar

@Composable
@Preview("Default Top App Bar Light Mode")
@Preview(
    name = "Default Top App Bar Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED,
    showBackground = true
)
fun DefaultTopAppBarPreview() {
    DefaultTopAppBar(title = "TopAppBar")
}
